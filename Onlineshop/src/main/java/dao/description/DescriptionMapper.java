package dao.description;

import dao.base.BaseDao;
import entity.Description;

public interface DescriptionMapper extends BaseDao<Description> {

	Description getByItem(int itemId);
}
