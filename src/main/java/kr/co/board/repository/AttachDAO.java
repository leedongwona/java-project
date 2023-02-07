package kr.co.board.repository;

import java.util.List;
import java.util.Map;

public interface AttachDAO {

	void insert(Map<String, Object> map);

	List<String> read(int bno);

	int deleteByFilename(String uploadedFilename);
}
