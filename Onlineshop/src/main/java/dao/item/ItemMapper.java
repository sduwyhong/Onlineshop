package dao.item;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import VO.SliderInfoVO;
import VO.SubItemVO;
import dao.base.BaseDao;
import entity.Item;

public interface ItemMapper extends BaseDao<Item> {
	
	Integer genId();

	List<Item> getByCategory(int categoryId);
	
	List<Item> retrieveByPrice(@Param("price1")int price1,@Param("price2")int price2);
	
	List<Item> getBySeller(String sellerId);
	
	int updateVolume(@Param("id") int ItemId, @Param("volume") int volume);
	
	int updateRepository(@Param("id") int ItemId, @Param("volume") int volume);
	
	int updateLikes();
	
	SubItemVO getSubItem(int id);

	List<Item> search(int[] index);

	List<SliderInfoVO> getSlider();

	int getRepository(Integer id);
}
