package kr.co.board.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.board.domain.BoardDTO;
import kr.co.board.domain.ReplyDTO;
import kr.co.board.repository.ReplyDAO;
import kr.co.common.domain.PageTO;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Inject
	private ReplyDAO rDao;

	@Override
	public void insert(Map<String, Object> map) {

		rDao.insert(map);

	}

	@Override
	public List<ReplyDTO> list(Integer bno) {

		return rDao.list(bno);
	}

	@Override
	public void update(Map<String, Object> map) {
		rDao.update(map);

	}

	@Override
	public Integer delete(Map<String, Object> map) {

		return rDao.delete(map);
	}

	@Override
	public PageTO<ReplyDTO> list(Map<String, String> map) {
		String scurpage = map.get("curpage");
		int curpage = 1;

		if (scurpage != null) {
			curpage = Integer.parseInt(scurpage);
		}

		int amount = rDao.getAmount(map);

		PageTO<ReplyDTO> pt = new PageTO<ReplyDTO>(curpage, amount);

		List<ReplyDTO> list = rDao.list(map, pt);

		pt.setList(list);

		return pt;
	}

}
