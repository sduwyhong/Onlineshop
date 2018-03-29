package dao.favorite;

import java.util.List;

import dao.base.BaseDao;
import entity.FavoriteItem;

public interface FavoriteItemMapper extends BaseDao<FavoriteItem> {

	List<FavoriteItem> getByFavorite(int favoriteId);
}
