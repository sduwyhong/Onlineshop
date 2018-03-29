package dao.review;

import java.util.List;

import VO.ReviewVO;
import dao.base.BaseDao;
import entity.Review;

public interface ReviewMapper extends BaseDao<Review> {

	List<ReviewVO> getReviewsByItem(Integer id);
	
	Review getReviewByOrder(String id);
}
