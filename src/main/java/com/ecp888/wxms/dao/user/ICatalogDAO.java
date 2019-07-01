package com.ecp888.wxms.dao.user;

import java.util.List;
import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.user.CatalogVO;

/**
 * 栏目DAO
 * @author ecp875
 * @since 2014-07-17
 */
public interface ICatalogDAO {

	/**
	 * 分页查询栏目信息
	 * @param CatalogVO
	 * @return
	 * @throws AppException
	 * @author ecp875
	 * @since 2014-07-17
	 */
	public List<CatalogVO> findCatalogList(CatalogVO catalogVO,PageVO pageVO) throws AppException;
	
	public int countCatalogList(CatalogVO catalogVO)throws AppException;
	
	
	/**
	 * 查询单条栏目信息
	 * @param CatalogVO
	 * @return
	 * @throws AppException
	 * @author ecp875
	 * @since 2014-07-17
	 */
	public CatalogVO findCatalog(Integer catalogId) throws AppException;
	
	
	/**
	 * 新建栏目信息
	 * @param CatalogVO
	 * @return
	 * @throws AppException
	 * @author ecp875
	 * @since 2014-07-17
	 */
	public int createCatalog(CatalogVO catalogVO) throws AppException;
	
	/**
	 * 更新栏目信息
	 * @param CatalogVO
	 * @return
	 * @throws AppException
	 * @author ecp875
	 * @since 2014-07-17
	 */
	public int updateCatalog(CatalogVO catalogVO) throws AppException;
	
	/**
	 * 删除栏目信息
	 * @param customerList
	 * @throws AppException
	 * @author ecp875
	 * @since 2014-07-17
	 */
	public void deleteCatalog(Integer catalogId)  throws AppException;
	
	/**
	 * 查找栏目树型结构
	 * @param catalogId
	 * @return
	 * @throws AppException
	 */
	public List<CatalogVO> findCatalogTree(String catalogId)throws AppException;
	
	/**
	 * 根据parentId查找栏目树型结构
	 * @param parentId
	 * @return
	 * @throws AppException
	 */
	public List<CatalogVO> findCatalogTreeByParentId(CatalogVO catalogVO)throws AppException;
	
	public int createRoleCatalog(CatalogVO catalogVO) throws AppException;
	
	public int deleteRoleCatalog(CatalogVO catalogVO) throws AppException;
	
	public List<String> findRoleCatalog(Integer catalogId) throws AppException;
	
}
