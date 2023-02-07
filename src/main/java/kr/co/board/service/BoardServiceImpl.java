package kr.co.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.board.domain.BoardDTO;
import kr.co.board.repository.AttachDAO;
import kr.co.board.repository.BoardDAO;
import kr.co.common.domain.PageTO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired // @inject 와 동일
	private BoardDAO bDao;

	@Autowired
	private AttachDAO aDao;

	@Override
	@Transactional
	public void insert(BoardDTO dto) throws Exception {

		bDao.insert(dto);

		List<String> list = dto.getUploadedFileList();

		for (int i = 0; i < list.size(); i++) {
			String uploadedFilename = list.get(i);

			Map<String, Object> map = new HashMap<String, Object>();

			map.put("uploadedFilename", uploadedFilename);
			map.put("bno", dto.getBno());

			aDao.insert(map);
		}

	}

	@Override
	public List<BoardDTO> list() {
		List<BoardDTO> list = bDao.list();
		return list;
	}

	@Override
	public PageTO<BoardDTO> list(Map<String, String> map) {
		int curpage = 1;

		String scurpage = map.get("curpage");
		if (scurpage != null) {
			curpage = Integer.parseInt(scurpage);
		}

		int amount = bDao.getAmount(map);

		PageTO<BoardDTO> pt = new PageTO<BoardDTO>(curpage, amount, map.get("criteria"), map.get("keyword"));

		List<BoardDTO> list = bDao.list(map, pt);

		pt.setList(list);

		return pt;
	}

	@Override
	public BoardDTO read(int bno) {
		bDao.increaseReadcnt(bno);
		BoardDTO dto = bDao.read(bno);

		return dto;
	}

	@Override
	public BoardDTO updateUI(Object bno) {

		return bDao.read(Integer.parseInt((String) bno));
	}

	@Override
	@Transactional
	public int update(BoardDTO dto, List<String> uploadedFileDeleteList) {
		int result = 1;

		result = bDao.update(dto);

		for (int i = 0; i < uploadedFileDeleteList.size(); i++) {
			String uploadedFilename = uploadedFileDeleteList.get(i);
			int ers = aDao.deleteByFilename(uploadedFilename);
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

			aDao.insert(map);
		}

		return result;
	}

	@Override
	public int update(Map<String, Object> map) {
		int result = 0;

		result = bDao.update(map);

		return result;
	}

	@Override
	public Integer delete(Map<String, Object> map) {
		Integer result = 0;
		result = bDao.delete(map);

		return result;
	}

	@Override
	public List<String> getFilenames(int bno) {

		return aDao.read(bno);
	}

}
