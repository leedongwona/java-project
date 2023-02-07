package kr.co.qna.repository;

import java.util.List;
import java.util.Map;

public interface QnaAttachDAO {

	void insert(Map<String, Object> map);

	List<String> read(int bno);

	int deleteByFilename(String uploadedFilename);
}
