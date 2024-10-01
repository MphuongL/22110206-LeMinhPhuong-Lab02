package vn.iostar.services.impl;

import java.util.List;

import vn.iostar.dao.ICategoryDao;
import vn.iostar.dao.impl.CategoryDaoImpl;
import vn.iostar.models.CategoryModel;
import vn.iostar.services.ICategoryService;

public class CategoryServiceImpl implements ICategoryService {

	public ICategoryDao cateDao =  new CategoryDaoImpl();
	@Override
	public List<CategoryModel> findAll() {
		// TODO Auto-generated method stub
		return cateDao.findAll();
	}
	
	@Override
	public CategoryModel findById(int id) {
		// TODO Auto-generated method stub
		return cateDao.findById(id);
	}

	@Override
	public void insert(CategoryModel category) {
		cateDao.insert(category);
		
	}

	@Override
	public void update(CategoryModel category) {
		CategoryModel cate = new CategoryModel();
		cate = cateDao.findById(category.getCategoryid());
		if(cate != null)
		{
			cateDao.update(category);
		}
	}

	@Override
	public void delete(int id) {
		CategoryModel cate = new CategoryModel();
		cate = cateDao.findById(id);
		if(cate != null)
		{
			cateDao.delete(id);
		}
		
	}

	@Override
	public List<CategoryModel> findName(String keyword) {
		// TODO Auto-generated method stub
		return cateDao.findName(keyword);
	}
	public static void main(String[] args) {
		CategoryServiceImpl userDao = new CategoryServiceImpl();
		
		System.out.println(userDao.findName("ip"));
		
	}
}
