/**
 * <p>ProjectBO.java </p>
 *
 * <p>系统名称: 青岛新建商品房备案系统<p>  
 * <p>功能描述: 开发项目BO<p>
 *
 * <p>公司名称: 上海南康科技有限公司<p> 
 * <p>作    者: Administrator</p>
 * <p>创建时间: 2017-4-1<p>
 * 
 */ 
package com.netcom.nkestate.fhhouse.project.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.MiniBO;
import com.netcom.nkestate.fhhouse.FHConstant;
import com.netcom.nkestate.fhhouse.company.vo.CompanyProjectVO;
import com.netcom.nkestate.fhhouse.interfaces.dao.RealestateDAO;
import com.netcom.nkestate.fhhouse.interfaces.vo.CHFlatVO;
import com.netcom.nkestate.fhhouse.project.dao.ProjectDAO;
import com.netcom.nkestate.fhhouse.project.vo.BuildingHouseVO;
import com.netcom.nkestate.fhhouse.project.vo.BuildingVO;
import com.netcom.nkestate.fhhouse.project.vo.HouseStateConsumeVO;
import com.netcom.nkestate.fhhouse.project.vo.HouseVO;
import com.netcom.nkestate.fhhouse.project.vo.PresellVO;
import com.netcom.nkestate.fhhouse.project.vo.ProjectVO;
import com.netcom.nkestate.fhhouse.project.vo.StartCompVO;
import com.netcom.nkestate.fhhouse.project.vo.StartUnitVO;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.dao.MetaField;
import com.netcom.nkestate.framework.util.DateUtil;
import com.netcom.nkestate.system.vo.SmUserVO;


public class ProjectBO extends MiniBO {

	static Logger logger = Logger.getLogger(ProjectBO.class.getName());

	private ProjectDAO projectDao = new ProjectDAO();

	/**
	 * 功能描述：查询开发企业下项目列表
	 * @param compId
	 * @return
	 * @throws Exception
	 */
	public List<ProjectVO> findCompanyProjectList(long compId,String logo,Page page) throws Exception {
		try{
			openDAO(projectDao);
			return projectDao.findCompanyProjectList(compId, logo, page);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(projectDao);
		}
	}

	/**
	 * 功能描述：项目新增
	 * @param projectVO
	 * @param compId
	 * @return
	 * @throws Exception
	 */
	public long saveProject(ProjectVO projectVO,long compId) throws Exception {
		try{
			openDAO(projectDao);
			projectDao.setTransaction();
			long projectId = projectDao.add(projectVO);
			//企业项目关联表 
			CompanyProjectVO cpvo = new CompanyProjectVO();
			cpvo.setComp_ID(compId);
			cpvo.setProject_ID(projectId);
			cpvo.setSerial(1);
			cpvo.setCreDate(projectVO.getCreDate());
			cpvo.setCreTime(projectVO.getCreTime());
			cpvo.setCrePerson(projectVO.getCrePerson());
			cpvo.setUpdDate(projectVO.getUpdDate());
			cpvo.setUpdTime(projectVO.getUpdTime());
			cpvo.setUpdPerson(projectVO.getUpdPerson());
			projectDao.add(cpvo);
			
			projectDao.commit();
			return projectId;
		}catch (Exception e){
			projectDao.rollback();
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(projectDao);
		}
	}

	/**
	 * 功能描述：查询项目下许可证列表
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	public List<StartUnitVO> findProjectPresellList(long projectId,Page page,String backFlag) throws Exception {
		try{
			openDAO(projectDao);
			return projectDao.findProjectPresellList(projectId, page, backFlag);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(projectDao);
		}
	}

	/**
	 * 功能描述：新增许可证
	 * @param presellVO
	 * @param unitVO
	 * @param compId
	 * @return
	 * @throws Exception
	 */
	public long savePresell(PresellVO presellVO,StartUnitVO unitVO,long compId) throws Exception {
		long startId = -1;
		try{
			openDAO(projectDao);
			projectDao.setTransaction();
			//新增许可证表 
			long presellId = projectDao.add(presellVO);

			//新增开盘单元表
			unitVO.setPresell_ID(presellId);
			startId = projectDao.add(unitVO);

			//新增开发企业开盘单元表
			StartCompVO scvo = new StartCompVO();
			scvo.setComp_ID(compId);
			scvo.setSerial(1);
			scvo.setStart_ID(startId);
			scvo.setCreDate(unitVO.getCreDate());
			scvo.setCreTime(unitVO.getCreTime());
			scvo.setCrePerson(unitVO.getCrePerson());
			scvo.setUpdDate(unitVO.getUpdDate());
			scvo.setUpdTime(unitVO.getUpdTime());
			scvo.setUpdPerson(unitVO.getUpdPerson());
			projectDao.add(scvo);

			projectDao.commit();

			return startId;
		}catch (Exception e){
			projectDao.rollback();
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(projectDao);
		}
	}

	/**
	 * 功能描述：更新许可证
	 * @param presellVO
	 * @param unitVO
	 * @param compId
	 * @return
	 * @throws Exception
	 */
	public long updatePresell(PresellVO presellVO,StartUnitVO unitVO,long compId) throws Exception {
		long count = -1;
		try{
			openDAO(projectDao);
			projectDao.setTransaction();
			//新增许可证表 
			count += projectDao.update2(presellVO);

			//更新开盘单元表
			count += projectDao.update2(unitVO);

			
			projectDao.commit();

			return count;
		}catch (Exception e){
			projectDao.rollback();
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(projectDao);
		}
	}

	/**
	 * 功能描述：查询开盘单元下的所有楼栋列表
	 * @param startId
	 * @return
	 * @throws Exception
	 */
	public List<BuildingVO> findStartUnitBuildingList(long startId) throws Exception {
		try{
			openDAO(projectDao);
			return projectDao.findStartUnitBuildingList(startId);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(projectDao);
		}
	}

	/**
	 * 功能描述：查询开盘单元下对应楼栋下的房屋列表
	 * @param startId
	 * @param buildingId
	 * @return
	 * @throws Exception
	 */
	public List<HouseVO> findStartUnitHouseListByBuidling(long startId,long buildingId) throws Exception {
		try{
			openDAO(projectDao);
			return projectDao.findStartUnitHouseListByBuidling(startId, buildingId);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(projectDao);
		}
	}

	/**
	 * 功能描述：开盘单元删除楼栋关联
	 * @param startId
	 * @param buildingId
	 * @param startUnitVO
	 * @return
	 * @throws Exception
	 */
	public long deleteStartBuidling(long startId,long buildingId,StartUnitVO startUnitVO) throws Exception {
		long count = 0;
		try{
			openDAO(projectDao);
			projectDao.setTransaction();
			//删除关联楼栋记录 
			BuildingHouseVO buildingHouseVO = new BuildingHouseVO();
			buildingHouseVO.setStart_ID(startId);
			buildingHouseVO.setBuilding_ID(buildingId);
			count += projectDao.deleteByTemplate(buildingHouseVO);

			//更新房屋套数
			count += projectDao.updateStartUnitHouseCount(startId);

			//更新开盘单元
			count += projectDao.update2(startUnitVO);

			projectDao.commit();
			return count;
		}catch (Exception e){
			projectDao.rollback();
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(projectDao);
		}
	}

	/**
	 * 功能描述：开盘单元楼栋关联保存
	 * @param projectId
	 * @param startId
	 * @param buildingList
	 * @param houseMap
	 * @param uservo
	 * @return
	 * @throws Exception
	 */
	public long saveStartBuidling(long projectId,long startId,List<BuildingHouseVO> buildingList,Map<String , List<BuildingHouseVO>> houseMap,SmUserVO uservo)
			throws Exception {
		long count = 0;
		RealestateDAO realestateDAO = new RealestateDAO();
		try{
			openDAO(realestateDAO);
			openDAO(projectDao);
			projectDao.setTransaction();
			for(BuildingHouseVO bhVO : buildingList){
				//一、判断楼栋是否存在，不存在则从楼盘表获取数据新增记录
				BuildingVO buildingVO = new BuildingVO();
				buildingVO.setBuilding_ID(bhVO.getBuilding_ID());
				buildingVO = (BuildingVO) projectDao.find(buildingVO);
				if(buildingVO == null){
					buildingVO = new BuildingVO();
					buildingVO.setBuilding_ID(bhVO.getBuilding_ID());
					buildingVO.setBuilding_Name(realestateDAO.findBuidlingLoaction(bhVO.getBuilding_ID()));
					buildingVO.setCreDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
					buildingVO.setCreTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
					buildingVO.setCrePerson(String.valueOf(uservo.getUserId()));
					buildingVO.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
					buildingVO.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
					buildingVO.setUpdPerson(String.valueOf(uservo.getUserId()));
					projectDao.add(buildingVO);
				}

				//二、删除楼栋原有关联记录
				projectDao.deleteBuildingHouse(startId, bhVO.getBuilding_ID());

				//获取当前楼栋对应房屋信息
				List<BuildingHouseVO> houseList = houseMap.get(bhVO.getBuilding_ID() + "");

				//三、新增关联房屋
				if(houseList != null && houseList.size() > 0){
					for(BuildingHouseVO hVO : houseList){
						//1、判断房屋是否存在，不存在则从楼盘表获取数据新增记录
						HouseVO houseVO = new HouseVO();
						houseVO.setHouse_ID(hVO.getHouse_ID());
						houseVO = (HouseVO) projectDao.find(houseVO);
						if(houseVO == null){
							//房屋信息查询
							CHFlatVO flatVO = realestateDAO.findCHFlat(hVO.getHouse_ID());
							//房屋登记状态查询
							HouseStateConsumeVO houseStateConsumeVO = new HouseStateConsumeVO();
							houseStateConsumeVO.setHouse_ID(hVO.getHouse_ID());
							houseStateConsumeVO = (HouseStateConsumeVO) projectDao.find(houseStateConsumeVO);
							int houseStatus = 9;
							if(houseStateConsumeVO != null){
								if(houseStateConsumeVO.getStatus() == FHConstant.DAJI_HOUSE_STATUS_ONLINE){
									houseStatus = FHConstant.HOUSE_STATUS_CANSELL;
								}else if(houseStateConsumeVO.getStatus() == FHConstant.DAJI_HOUSE_STATUS_REGISTED){
									houseStatus = FHConstant.HOUSE_STATUS_REGISTED;
								}else if(houseStateConsumeVO.getStatus() == FHConstant.DAJI_HOUSE_STATUS_UNONLINE){
									houseStatus = FHConstant.HOUSE_STATUS_UNACTIVE;
								}else if(houseStateConsumeVO.getStatus() == FHConstant.DAJI_HOUSE_STATUS_CANCEL){
									houseStatus = FHConstant.HOUSE_STATUS_SIGNED;
								}
							}

							houseVO = new HouseVO();
							houseVO.setHouse_ID(hVO.getHouse_ID());
							houseVO.setDistrictID(Integer.parseInt(flatVO.getDistrictID()));
							houseVO.setStatus(houseStatus);
							houseVO.setManual_Status(1);
							houseVO.setNoXgState(FHConstant.HOUSE_NOXGSTATE_YES);//默认房屋为限购
							if(flatVO.getFlarea()!=null && !"".equals(flatVO.getFlarea())&&Double.parseDouble(flatVO.getFlarea())>0){
								houseVO.setSell_Plan_ID(flatVO.getPlan_ID());
							}else{
								houseVO.setPre_Plan_ID(flatVO.getPlan_ID() );
							}
							houseVO.setBuilding_ID(flatVO.getBuildingID());
							houseVO.setRoom_Number(flatVO.getRoom_Number());
							houseVO.setFlarea(flatVO.getFlarea());
							houseVO.setPriv_FlArea(flatVO.getPriv_Flarea());
							houseVO.setCo_FlArea(flatVO.getCo_Flarea());
							houseVO.setEx_FlArea(flatVO.getEx_Flarea());
							houseVO.setRatial(flatVO.getRatial());
							houseVO.setPlan_FlArea(flatVO.getPlan_Flarea());
							houseVO.setPlan_Priv_FlArea(flatVO.getPlan_Priv_Flarea());
							houseVO.setPlan_Co_FlArea(flatVO.getPlan_Co_Flarea());
							houseVO.setPlan_Ex_FlArea(flatVO.getPlan_Ex_Flarea());
							houseVO.setPlan_Ratial(flatVO.getPlan_Ratial());
							houseVO.setHouse_Number(flatVO.getHouse_Number());
							houseVO.setFlat_Type(flatVO.getFlat_Type());
							houseVO.setRight_of_Owner(flatVO.getRight_Of_Owner());
							houseVO.setArea(flatVO.getPro_Area());
							houseVO.setArea(null);
							houseVO.setCo_Area(null);
							houseVO.setPro_Area(null);
							houseVO.setPriv_Area(null);
							houseVO.setEx_Area(null);
							houseVO.setAdv_Area(null);
							houseVO.setBuilding_Type(flatVO.getBuilding_Type());
							houseVO.setFlat_Type(flatVO.getFlat_Type());
							houseVO.setLand_Use(flatVO.getHouse_Use());
							houseVO.setFloor(flatVO.getFloor_From());
							houseVO.setFloor_Name(flatVO.getFloor_Name());
							houseVO.setStart_Date(flatVO.getState_Date());
							houseVO.setLand_Descent(null);
							houseVO.setHome_Descent(flatVO.getHome_Descent());
							houseVO.seteOwner(null);
							houseVO.setVocation(null);
							houseVO.setData_State(null);
							houseVO.setLand_Use2(null);
							houseVO.setCellar_Area(flatVO.getCellar_Area());
							houseVO.setPlan_Cellar_Area(flatVO.getPlan_Cellar_Area());
							houseVO.setHouse_Type(flatVO.getAttribute("house_type") == null ? "" : flatVO.getAttribute("house_type").toString());
							houseVO.setCreDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
							houseVO.setCreTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
							houseVO.setCrePerson(String.valueOf(uservo.getUserId()));
							houseVO.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
							houseVO.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
							houseVO.setUpdPerson(String.valueOf(uservo.getUserId()));
							projectDao.add(houseVO);

							/*
							 * //2、房屋平面图表 if(flatVO.getPlan_ID()!=null && !"".equals(flatVO.getPlan_ID())){ HousePlanVO housePlanVO = new HousePlanVO();
							 * housePlanVO.setPlan_ID(Long.parseLong(flatVO.getPlan_ID())); HousePlanVO tempHousePlanVO = (HousePlanVO) projectDao.find(housePlanVO); if(tempHousePlanVO == null){
							 * housePlanVO.setCreDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD())); housePlanVO.setCreTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
							 * housePlanVO.setCrePerson(uservo.getLoginName()); housePlanVO.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
							 * housePlanVO.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS())); housePlanVO.setUpdPerson(uservo.getLoginName()); projectDao.add(housePlanVO); } }
							 */


						}
						//3、开盘单元楼栋房屋关联表
						BuildingHouseVO buildingHouseVO = new BuildingHouseVO();
						buildingHouseVO.setHouse_ID(hVO.getHouse_ID());
						BuildingHouseVO tempvo = (BuildingHouseVO) projectDao.find(buildingHouseVO);
						if(tempvo == null){
							buildingHouseVO.setBuilding_ID(bhVO.getBuilding_ID());
							buildingHouseVO.setStart_ID(startId);
							buildingHouseVO.setReference_Price(bhVO.getReference_Price());
							buildingHouseVO.setRatio(bhVO.getRatio());
							buildingHouseVO.setCreDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
							buildingHouseVO.setCreTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
							buildingHouseVO.setCrePerson(String.valueOf(uservo.getUserId()));
							buildingHouseVO.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
							buildingHouseVO.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
							buildingHouseVO.setUpdPerson(String.valueOf(uservo.getUserId()));
							projectDao.add(buildingHouseVO);

						}

					}
				}
				

				//四、更新房屋参考价格
				bhVO.setStart_ID(startId);
				bhVO.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
				bhVO.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
				bhVO.setUpdPerson(String.valueOf(uservo.getUserId()));
				//条件
				List<MetaField> whereList = new ArrayList<MetaField>();
				whereList.add(new MetaField("start_ID",startId));
				whereList.add(new MetaField("building_ID",bhVO.getBuilding_ID()));
				//更新字段
				List<MetaField> fieldList = new ArrayList<MetaField>();
				fieldList.add(new MetaField("reference_Price",bhVO.getReference_Price()));
				fieldList.add(new MetaField("ratio",bhVO.getRatio()));
				fieldList.add(new MetaField("updPerson",uservo.getLoginName()));
				fieldList.add(new MetaField("updDate",DateUtil.getSysDateYYYYMMDD()));
				fieldList.add(new MetaField("updTime",DateUtil.getSysDateHHMMSS()));
				projectDao.update(BuildingHouseVO.class, whereList, fieldList);
			}
			//五、更新开盘单元状态
			StartUnitVO startUnitVO = new StartUnitVO();
			startUnitVO.setStart_ID(startId);
			startUnitVO.setStatus(FHConstant.START_UNIT_STATUS_EDIT);//编辑状态
			startUnitVO.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
			startUnitVO.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
			startUnitVO.setUpdPerson(String.valueOf(uservo.getUserId()));
			projectDao.update2(startUnitVO);

			//六、更新开盘单元可售房屋套数
			projectDao.updateStartUnitHouseCount(startId);

			projectDao.commit();
			return count;
		}catch (Exception e){
			projectDao.rollback();
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(projectDao);
			closeDAO(realestateDAO);
		}
	}

	/**
	 * 功能描述：房屋关联查询房屋列表
	 * @param buildingId
	 * @return
	 * @throws Exception
	 */
	public List<CHFlatVO> findBuildHouseList(long buildingId) throws Exception {
		try{
			openDAO(projectDao);
			return projectDao.findBuildHouseList(buildingId);
		}catch (Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}finally{
			closeDAO(projectDao);
		}
	}

}
