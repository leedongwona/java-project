package kr.co.qna.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.qna.domain.QnaDTO;
import kr.co.qna.repository.QnaAttachDAO;
import kr.co.qna.repository.QnaDAO;
import kr.co.common.domain.PageTO;

@Service
public class QnaServiceImpl implements QnaService {

	@Autowired // @inject 와 동일
	private QnaDAO qDao;

	@Autowired
	private QnaAttachDAO aqDao;

	@Override
	@Transactional
	public void insert(QnaDTO dto) throws Exception {

		qDao.insert(dto);

		List<String> list = dto.getUploadedFileList();

		for (int i = 0; i < list.size(); i++) {
			String uploadedFilename = list.get(i);

			Map<String, Object> map = new HashMap<String, Object>();

			map.put("uploadedFilename", uploadedFilename);
			map.put("bno", dto.getBno());

			aqDao.insert(map);
		}

	}

	@Override
	public List<QnaDTO> list() {
		List<QnaDTO> list = qDao.list();
		return list;
	}

	@Override
	public PageTO<QnaDTO> list(Map<String, String> map) {
		int curpage = 1;

		String scurpage = map.get("curpage");
		if (scurpage != null) {
			curpage = Integer.parseInt(scurpage);
		}

		int amount = qDao.getAmount(map);

		PageTO<QnaDTO> pt = new PageTO<QnaDTO>(curpage, amount, map.get("criteria"), map.get("keyword"));

		List<QnaDTO> list = qDao.list(map, pt);

		pt.setList(list);

		return pt;
	}

	@Override
	public QnaDTO read(int bno) {
		qDao.increaseReadcnt(bno);
		QnaDTO dto = qDao.read(bno);

		return dto;
	}

	@Override
	public QnaDTO updateUI(Object bno) {

		return qDao.read(Integer.parseInt((String) bno));
	}

	@Override
	@Transactional
	public int update(QnaDTO dto, List<String> uploadedFileDeleteList) {
		int result = 1;

		result = qDao.update(dto);

		for (int i = 0; i < uploadedFileDeleteList.size(); i++) {
			String uploadedFilename = uploadedFileDeleteList.get(i);
			int ers = aqDao.deleteByFilename(uploadedFilename);
			if (ers < 1) {

				result = 0;

				break;
			}
		}

		List<String> list = dto.getUploadedFileList();

		for (int i = 0; i < list.size(); i++) {
			String uploadedFilename = list.get(i);

			Map<String, Object> map = new HashMap<String, Object>();

			map.put("uploadedFilename", uploadedFilename);
			map.put("bno", dto.getBno());

			aqDao.insert(map);
		}

		return result;
	}

	@Override
	public int update(Map<String, Object> map) {
		int result = 0;

		result = qDao.update(map);

		return result;
	}

	@Override
	public Integer delete(Map<String, Object> map) {
		Integer result = 0;
		result = qDao.delete(map);

		return result;
	}

	@Override
	public List<String> getFilenames(int bno) {

		return aqDao.read(bno);
	}

}
