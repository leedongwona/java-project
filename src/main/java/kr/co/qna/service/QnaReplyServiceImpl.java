package kr.co.qna.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.qna.domain.QnaDTO;
import kr.co.qna.domain.QnaReplyDTO;
import kr.co.qna.repository.QnaReplyDAO;
import kr.co.common.domain.PageTO;

@Service
public class QnaReplyServiceImpl implements QnaReplyService {

	@Inject
	private QnaReplyDAO qrDao;

	@Override
	public void insert(Map<String, Object> map) {

		qrDao.insert(map);

	}

	@Override
	public List<QnaReplyDTO> list(Integer bno) {

		return qrDao.list(bno);
	}

	@Override
	public void update(Map<String, Object> map) {
		qrDao.update(map);

	}

	@Override
	public Integer delete(Map<String, Object> map) {

		return qrDao.delete(map);
	}

	@Override
	public PageTO<QnaReplyDTO> list(Map<String, String> map) {
		String scurpage = map.get("curpage");
		int curpage = 1;

		if (scurpage != null) {
			curpage = Integer.parseInt(scurpage);
		}

		int amount = qrDao.getAmount(map);

		PageTO<QnaReplyDTO> pt = new PageTO<QnaReplyDTO>(curpage, amount);

		List<QnaReplyDTO> list = qrDao.list(map, pt);

		pt.setList(list);

		return pt;
	}

}
