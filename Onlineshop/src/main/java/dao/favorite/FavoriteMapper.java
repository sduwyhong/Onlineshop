package dao.favorite;

import org.apache.ibatis.annotations.Param;

import dao.base.BaseDao;
import entity.Favorite;

public interface FavoriteMapper extends BaseDao<Favorite> {

	Favorite getByUser(@Param ("id") String userId);
}
