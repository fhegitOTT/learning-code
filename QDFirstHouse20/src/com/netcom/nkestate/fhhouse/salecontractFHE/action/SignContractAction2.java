package com.netcom.nkestate.fhhouse.salecontractFHE.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSON;
import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netcom.nkestate.common.BaseAction;
import com.netcom.nkestate.common.Constant;
import com.netcom.nkestate.fhhouse.FHConstant;
import com.netcom.nkestate.fhhouse.company.vo.EnterpriseQualifyVO;
import com.netcom.nkestate.fhhouse.company.vo.SignerVO;
import com.netcom.nkestate.fhhouse.interfaces.action.RealestateFinder;
import com.netcom.nkestate.fhhouse.interfaces.bo.RealestateBO;
import com.netcom.nkestate.fhhouse.interfaces.external.ZBReader;
import com.netcom.nkestate.fhhouse.interfaces.vo.CHFlatVO;
import com.netcom.nkestate.fhhouse.interfaces.vo.CHLocationVO;
import com.netcom.nkestate.fhhouse.interfaces.vo.DocMessageVO;
import com.netcom.nkestate.fhhouse.manage.vo.HMFCityVO;
import com.netcom.nkestate.fhhouse.manage.vo.HouseBandAverageVO;
import com.netcom.nkestate.fhhouse.project.vo.BuildingVO;
import com.netcom.nkestate.fhhouse.project.vo.CompCancelPwdVO;
import com.netcom.nkestate.fhhouse.project.vo.HouseVO;
import com.netcom.nkestate.fhhouse.project.vo.ProPreBldSignerVO;
import com.netcom.nkestate.fhhouse.project.vo.StartUnitVO;
import com.netcom.nkestate.fhhouse.query.bo.ContractPdfBO;
import com.netcom.nkestate.fhhouse.query.bo.ContractQueryBO;
import com.netcom.nkestate.fhhouse.query.vo.ContractSignPdfVO;
import com.netcom.nkestate.fhhouse.salecontract.action.SignContractAction;
import com.netcom.nkestate.fhhouse.salecontract.bo.SignContractBO;
import com.netcom.nkestate.fhhouse.salecontract.vo.Attach4HireVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.Attach4LimitVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.Attach4OtherVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.Attach4RealVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.Attach4VO;
import com.netcom.nkestate.fhhouse.salecontract.vo.AttachVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.BuyerInfoVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.BuyerInfoVOFHE;
import com.netcom.nkestate.fhhouse.salecontract.vo.ContractCancelPwdVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.ContractDealVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.ContractDetailCsVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.ContractDetailYsVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.EarnestContractVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.PresellAttach2MoneyVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.SellerInfoVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.XgBuyerOwnerNameVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.XgLimitSaleContractVO;
import com.netcom.nkestate.fhhouse.salecontractFHE.vo.ContractDealFHEVO;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.dao.MetaField;
import com.netcom.nkestate.framework.dao.MetaFilter;
import com.netcom.nkestate.framework.dao.MetaOrder;
import com.netcom.nkestate.framework.html.HtmlTableUtil;
import com.netcom.nkestate.framework.html.TableProperty;
import com.netcom.nkestate.framework.util.DateUtil;
import com.netcom.nkestate.system.bo.DictionaryBO;
import com.netcom.nkestate.system.bo.SystemBO;
import com.netcom.nkestate.system.vo.SmUserVO;
@Controller
@RequestMapping("/outer/signcontractFHE")
public class SignContractAction2 extends BaseAction {
    static Logger logger = Logger.getLogger(SignContractAction.class.getName());

	@RequestMapping("/test")
	public String test() {
		return "/fhe/salecontract/signcontract/ContractCreate";
	}

    /**
	 * 功能描述：签约查询
	 * @return
	 */
	@RequestMapping("/querySignContractFHE")
    public String querySignContract() {
        try{
			return "/fhe/salecontract/signcontract/SignContractQuery";
        }catch (Exception e){
            e.printStackTrace();
            return ERROR_System;
        }
    }

    /**
	 * 功能描述：签约查询检查
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/doQueryCheckFHE")
    @ResponseBody
    public JSONArray delContractID(HttpServletRequest request,HttpSession session) {
        JSONArray json = new JSONArray();
        Map<String , Object> map = new HashMap<String , Object>();
        SmUserVO uservo = (SmUserVO) session.getAttribute("LgUser");
        SignContractBO scBO = new SignContractBO();
		String buildingID = request.getParameter("buildingID"); //楼栋
        try{
            List<MetaFilter> params1 = new ArrayList<MetaFilter>();
            params1.add(new MetaFilter("building_ID", "=", buildingID));
            params1.add(new MetaFilter("signer_ID", "=", uservo.getUserId()));
            List<ProPreBldSignerVO> list1 = scBO.search(ProPreBldSignerVO.class, params1);
            if(list1 != null && list1.size() > 0){
				BuildingVO bvo = (BuildingVO) scBO.find(BuildingVO.class, Long.parseLong(buildingID)); //// 根据ID查找对象
                if(bvo != null){
                    List<CHFlatVO> list2 = scBO.queryHouseList(buildingID);
                    if(list2 != null && list2.size() > 0){
                        map.put("result", "success");
                        map.put("message", "");
                    }else{
                        map.put("result", "fail");
						map.put("message", "该楼栋不存在房屋！");
                    }
                }else{
                    map.put("result", "fail");
					map.put("message", "该楼栋已被移出，不属于本开盘单元！");
                }
            }else{
                map.put("result", "fail");
				map.put("message", "该楼栋已被移出，不属于签约人有权签约范围！");
            }
            json = JSONArray.fromObject(map);
            return json;
        }catch (Exception e){
            e.printStackTrace();
            map.put("result", "fail");
			map.put("message", "房屋查询失败！");
            json = JSONArray.fromObject(map);
            return json;
        }
    }

    /**
	 * 功能描述：签约查询列表
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/querySignContractListFHE")
	public String querySignContractListFHE(HttpServletRequest request,HttpSession session) {
        try{
            SmUserVO uservo = (SmUserVO) session.getAttribute("LgUser");
            SignContractBO scBO = new SignContractBO();
            RealestateBO rBo = new RealestateBO();
			String projectID = request.getParameter("projectID"); //项目
			String startID = request.getParameter("startID"); //许可证
			String buildingID = request.getParameter("buildingID"); //楼栋
			List<HouseVO> list = scBO.queryUserHouseListFHE(uservo.getUserId(), startID, buildingID); //查询用户操作权限下的楼栋房屋列表
			List<HouseVO> list1 = rBo.findFloor(buildingID); //顺序查询房屋的层数
			request.setAttribute("list1", list1); //楼栋的房屋层数
			request.setAttribute("list", list); //楼栋的房屋列表
			request.setAttribute("projectID", projectID); //项目
			request.setAttribute("startID", startID); //许可证
			return "/fhe/salecontract/signcontract/SignContractQueryList";
        }catch (Exception e){
            e.printStackTrace();
            return ERROR_System;
        }
    }


    /**
	 * 功能描述：房屋信息
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/doHouseViewFHE")
	public String doHouseViewFHE(HttpServletRequest request,HttpSession session) {
        try{
            String projectID = request.getParameter("projectID");
            String startID = request.getParameter("startID");
			String houseId = request.getParameter("houseId");

            SignContractBO scBO = new SignContractBO();
            HouseVO hvo = (HouseVO) scBO.find(HouseVO.class, Long.parseLong(houseId));
            request.setAttribute("houseStatus", hvo.getStatus());
            RealestateBO rBo = new RealestateBO();

			/* 房屋信息 */
            CHFlatVO chFlatVO = rBo.findhouseInfo(houseId);
            request.setAttribute("chFlatVO", chFlatVO);
			/* 产权信息 */
            List<CHFlatVO> arList = rBo.findRealInfo(houseId);
            request.setAttribute("arList", arList);
			/* 许可证信息 */
            List<HouseVO> permitList = rBo.queryPermitInfo(houseId);
			request.setAttribute("permitList", permitList);
			String ctype = "-1";//合同类型选择标志  -1：全不能选择   1：预售  2：出售
			if(permitList != null && permitList.size() > 0){
                ctype = "1";
            }
			//查询房屋是否存在1034网签申请备案信息
            if(rBo.checkHouseHasFileReg(houseId)){
                ctype = "2";
            }else{
				//String typeid = rBo.findTypebid(houseId);//获取产权登记类别
                if(arList != null && arList.size() > 0){
                    CHFlatVO tempvo = (CHFlatVO) arList.get(0);
                    String typeid = tempvo.getAttribute("typebid").toString();
                    if("116".equals(typeid) || "115".equals(typeid)){
                        ctype = "2";
                    }else if("128".equals(typeid)){
                        ctype = "-1";
                    }
                }
            }

			/* 查看房屋有无定金合同ID */
            String earnestID = "";
            List<MetaFilter> params = new ArrayList<MetaFilter>();
            params.add(new MetaFilter("houseID", "=", houseId));
            params.add(new MetaFilter("status", "=", FHConstant.CONTRACT_STATUS_SIGNED));
            params.add(new MetaFilter("type", "=", FHConstant.CONTRACT_TYPE_EARNEST));
            List<ContractDealVO> list = scBO.search(ContractDealVO.class, params);
            if(list != null && list.size() > 0){
                earnestID = String.valueOf(list.get(0).getContractID());
            }
            request.setAttribute("earnestID", earnestID);


            request.setAttribute("ctype", ctype);
			request.setAttribute("houseId", houseId);
            request.setAttribute("projectID", projectID);
            request.setAttribute("startID", startID);
			return "/fhe/salecontract/signcontract/HouseInfo";
        }catch (Exception e){
            e.printStackTrace();
            return ERROR_System;
        }
    }

    /**
	 * 功能描述：签约合同检查
	 * @param request
	 * @param session
	 * @param eqvo
	 * @return
	 */
	@RequestMapping("/signContractCheckFHE")
    @ResponseBody
	public JSONArray signContractCheckFHE(HttpServletRequest request,HttpSession session) {
        JSONArray json = new JSONArray();
        Map<String , Object> map = new HashMap<String , Object>();
        try{
            SmUserVO uservo = (SmUserVO) session.getAttribute("LgUser");
			String signerId = String.valueOf(uservo.getUserId());
            String startId = request.getParameter("startID");
            String houseId = request.getParameter("houseId");


            SignContractBO sBo = new SignContractBO();
            RealestateBO rBo = new RealestateBO();
			int startResult = sBo.checkHouseExist(startId, houseId); //检查房屋是否在开盘单元下
            if(startResult > 0){
				int signerResult = sBo.checkSignerHouseRelate(signerId, houseId); //检查签约人是否有这个房屋的签约权
                if(signerResult > 0){
                    CHFlatVO chFlatVO = (CHFlatVO) rBo.find(CHFlatVO.class, Long.parseLong(houseId));
                    map.put("result", "success");
                    map.put("message", "");

                    if(chFlatVO != null){
                        HouseVO hvo = (HouseVO) sBo.find(HouseVO.class, Long.parseLong(houseId));
                    }else{
                        map.put("result", "fail");
						map.put("message", "该房屋在楼盘表中已不存在，不能签约！");
                    }

                }else{
                    map.put("result", "fail");
					map.put("message", "该房屋所属楼幢已不属于本签约人，不能签约！");
                }
            }else{
                map.put("result", "fail");
				map.put("message", "该房屋已不属于本开盘单元，不能签约！");
            }

            json = JSONArray.fromObject(map);
            return json;
        }catch (Exception e){
            e.printStackTrace();
            map.put("result", "fail");
			map.put("message", "合同签约检查失败");
            json = JSONArray.fromObject(map);
            return json;
        }
    }


    /**
	 * 功能描述：转到新建合同
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/createContractFHEBack")
	public String createContractBack(HttpServletRequest request,HttpSession session) throws Exception {
		String contractID = request.getParameter("contractID");
		SignContractBO scBO = new SignContractBO();
		request.setAttribute("contractID", contractID);
		List<MetaFilter> params = new ArrayList<MetaFilter>();
		params.add(new MetaFilter("contractID", "=", contractID)); //添加条件，
		List<MetaOrder> orders = new ArrayList<MetaOrder>();
		orders.add(new MetaOrder("serial", MetaOrder.ASC)); //添加排序，按serial属性升序排序
		List<BuyerInfoVOFHE> buyerList = scBO.search(BuyerInfoVOFHE.class, params, orders, null); //查找条件：contractID=contractID，排序方式：按serial属性升序排序，分页
		request.getSession().setAttribute("buyerList", buyerList);

		return "/fhe/salecontract/signcontract/ContractCreate";
	}


	/**
	 * 功能描述：转到新建合同
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/createContractFHE")
	public String createContract(HttpServletRequest request,HttpSession session) throws Exception {
		SignContractBO scBO = new SignContractBO();
		SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
		String person = vo.getLoginName();
		RealestateBO rBo = new RealestateBO();
		DictionaryBO dictBO = new DictionaryBO();


		long dates = Long.parseLong(DateUtil.getSysDateYYYYMMDD());
		long times = Long.parseLong(DateUtil.getSysDateHHMMSS());

		String type = "2";
		String houseId = request.getParameter("houseId");
		String startID = request.getParameter("startID");
		String projectID = request.getParameter("projectID");
		request.setAttribute("houseId", houseId);
		request.setAttribute("startID", startID);
		request.setAttribute("projectID", projectID);

		long contractID = 0;

		//获取企业信息
		EnterpriseQualifyVO eqvo = (EnterpriseQualifyVO) scBO.find(EnterpriseQualifyVO.class, vo.getOrgID());
		//合同甲方
		SellerInfoVO siVo = new SellerInfoVO();
		siVo.setCompID(vo.getOrgID());
		siVo.setSerial(1);
		siVo.setSellerName(eqvo.getName());
		siVo.setSellerAddress(eqvo.getRegadr());
		siVo.setSellerPostcode(eqvo.getPost());
		siVo.setSellerBizcardcode(eqvo.getBizregister_Num());
		siVo.setSellerCertcode(eqvo.getAptitudeNum());
		siVo.setSellerDelegate(eqvo.getDelegate());
		siVo.setSellerDlgCall(eqvo.getDlg_Call());
		siVo.setCrePerson(person);
		siVo.setCreDate(dates);
		siVo.setCreTime(times);
		siVo.setUpdPerson(person);
		siVo.setUpdDate(dates);
		siVo.setUpdTime(times);

		//合同主信息
		ContractDealVO cdVo = new ContractDealVO();
		if(projectID != null && !"".equals(projectID)){
			cdVo.setProjectID(Long.parseLong(projectID));
		}
		if(startID != null && !"".equals(startID)){
			cdVo.setStartID(Long.parseLong(startID));
			StartUnitVO suvo = (StartUnitVO) scBO.find(StartUnitVO.class, Long.parseLong(startID));
			cdVo.setPresellID(suvo.getPresell_ID());
		}
		if(houseId != null && !"".equals(houseId)){
			cdVo.setHouseID(Long.parseLong(houseId));
		}
		//  cdVo.setTemplateId(templateID);

		CHFlatVO chFlatVO = (CHFlatVO) rBo.find(CHFlatVO.class, Long.parseLong(houseId));
		//坐落查询
		CHLocationVO locationVo = rBo.findLocationInfo(houseId);
		if(locationVo != null){
			cdVo.setRoad(locationVo.getRoadName());
			cdVo.setLaneName(locationVo.getLaneName());
			cdVo.setSubLane(locationVo.getSubLane());
			cdVo.setBuildingNumber(locationVo.getStreetNumber());
			cdVo.setAlley(locationVo.getLaneName() + locationVo.getSubLane());
		}

		cdVo.setLandUseCode(Long.parseLong(chFlatVO.getHouse_Use())); //房屋用途代码
		DictionaryBO dicBO = new DictionaryBO();
		int landType = dicBO.getHouseLandType(chFlatVO.getHouse_Use());
		cdVo.setLandType(landType);//备案系统房屋用 1:住宅、2:办公、3:商铺、4:其他
		cdVo.setSigner(String.valueOf(vo.getUserId())); //销售人账号
		cdVo.setSignDate(DateUtil.getSysDateYYYYMMDD() + DateUtil.getSysDateHHMMSS());
		cdVo.setCrePerson(person);
		cdVo.setCreDate(dates);
		cdVo.setCreTime(times);
		cdVo.setUpdPerson(person);
		cdVo.setUpdDate(dates);
		cdVo.setUpdTime(times);
		if(type != null && !"".equals(type)){
			cdVo.setType(Integer.parseInt(type));
		}
		//合同附件
		List<AttachVO> attachList = new ArrayList<AttachVO>();
		if(type != null && "2".equals(type)){
			cdVo.setContractversion(FHConstant.NEW_SC_CONTRACTVERSION);
			//出售合同
			ContractDetailCsVO scVo = new ContractDetailCsVO();
			scVo.setContractID(Long.parseLong(new SystemBO().getSequence("CONTRACTID")));
			scVo.setCrePerson(person);
			scVo.setCreDate(dates);
			scVo.setCreTime(times);
			scVo.setUpdPerson(person);
			scVo.setUpdDate(dates);
			scVo.setUpdTime(times);
			//合同附件
			int attachSerial[] = { FHConstant.ATTACH_SERIAL_SFJ2, FHConstant.ATTACH_SERIAL_SFJ3, FHConstant.ATTACH_SERIAL_SFJ4, FHConstant.ATTACH_SERIAL_SFJ5, FHConstant.ATTACH_SERIAL_SFJ6, FHConstant.ATTACH_SERIAL_SFJ9, FHConstant.ATTACH_SERIAL_SFJ10, FHConstant.ATTACH_SERIAL_SFJ11,
					FHConstant.ATTACH_SERIAL_SFJ12 };
			for(int j = 0; j < attachSerial.length; j++){
				AttachVO aVo = new AttachVO();
				aVo.setSerial(attachSerial[j]);
				aVo.setContent("".getBytes());
				aVo.setCrePerson(person);
				aVo.setCreDate(dates);
				aVo.setCreTime(times);
				aVo.setUpdPerson(person);
				aVo.setUpdDate(dates);
				aVo.setUpdTime(times);
				attachList.add(aVo);
			}
			contractID = scBO.addSignContract(null, scVo, null, siVo, null, cdVo, type, attachList, person, dates, times);
		}
		request.setAttribute("contractID", contractID);
		request.getSession().setAttribute("contractID", contractID);

		request.setAttribute("buyerList", "");
		return "/fhe/salecontract/signcontract/ContractCreate";

	}
	
	/**
	 * 功能描述：转到乙方列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findBuyerList")
	@ResponseBody
	public Map<String , Object> BuyerList(HttpServletRequest request,HttpSession session) {
		JSONArray json = new JSONArray();
		try{
			String contractID = request.getParameter("contractID");
			SignContractBO scBO = new SignContractBO();
			if(contractID != null && !"0".equals(contractID)){ //存在合同号，查询乙方信息列表
				Map<String , Object> result = new HashMap<String , Object>();
				List<MetaFilter> params = new ArrayList<MetaFilter>();
				params.add(new MetaFilter("contractID", "=", contractID)); //添加条件，
				List<MetaOrder> orders = new ArrayList<MetaOrder>();
				orders.add(new MetaOrder("serial", MetaOrder.ASC)); //添加排序，按serial属性升序排序
				List<BuyerInfoVOFHE> buyerList = scBO.search(BuyerInfoVOFHE.class, params, orders, null); //查找条件：contractID=contractID，排序方式：按serial属性升序排序，分页
				//				JSONArray array = JSONArray.fromObject(buyerList);
				result.put("code", 0);
				result.put("msg", "");
				result.put("count", buyerList.size());
				result.put("data", buyerList);
				//				json = JSONArray.fromObject(result);
				System.out.println("-------------------");
				System.out.println(result.toString());
				//request.getSession().setAttribute("buyerList", buyerList);
				return result;
			}
		}catch (Exception e){
			throw new RuntimeException();
			//e.printStackTrace();
		}
		return null;

	}


	@RequestMapping(value = "/editBuyer1FHE2")
	public String editBuyer1FHE2(HttpServletRequest request,Page page,HttpSession session) {
		return "/fhe/salecontract/signcontract/BuyerInfo1";

	}

	/**
	 * 功能描述：转到乙方编辑（页面跳转）
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/editBuyer1FHE")
	public String editBuyer1FHE(HttpServletRequest request,Page page,HttpSession session) {
		try{
			String contractID = request.getParameter("contractID");
			SignContractBO scBO = new SignContractBO();

			if(contractID != null && !"0".equals(contractID)){ //存在合同号，查询乙方信息列表
				List<MetaFilter> params = new ArrayList<MetaFilter>();
				params.add(new MetaFilter("contractID", "=", contractID)); //添加条件，
				List<MetaOrder> orders = new ArrayList<MetaOrder>();
				orders.add(new MetaOrder("serial", MetaOrder.ASC)); //添加排序，按serial属性升序排序
				List<BuyerInfoVOFHE> buyerList = scBO.search(BuyerInfoVOFHE.class, params, orders, page); //查找条件：contractID=contractID，排序方式：按serial属性升序排序，分页
				//buyerList.get(i).getBuyerSex() = 1
				request.getSession().setAttribute("buyerList", buyerList);
            }
			request.getSession().setAttribute("contractID", contractID);
			//return "/fhe/salecontract/signcontract/BuyerInfo1";
			return "/fhe/salecontract/signcontract/ContractCreate";
        }catch (Exception e){
            e.printStackTrace();
            return ERROR_System;
        }
    }

	/**
	 * 功能描述：转到乙方编辑（信息修改）
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/operaBuyerInfoFHE")
	public String operaBuyerInfo(HttpServletRequest request) {
		try{
			String contractID = request.getParameter("contractID");
			String serial = request.getParameter("serial");
			SignContractBO scBO = new SignContractBO();
			if(contractID != null && !"".equals(contractID) && serial != null && !"".equals(serial)){ //存在合同编号、乙方顺序，查询乙方信息
				BuyerInfoVO biVo = new BuyerInfoVO();
				biVo.setContractID(Long.parseLong(contractID));
				biVo.setSerial(Integer.parseInt(serial));
				biVo = (BuyerInfoVO) scBO.find(biVo);
				request.setAttribute("buyer", biVo);
			}
			request.getSession().setAttribute("contractID", contractID);
			//return "/fhe/salecontract/signcontract/BuyerInfoSave";
			return "/fhe/salecontract/signcontract/BuyerInfo1";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：转到甲方编辑
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/editSellerFHE")
	public String editSellerFHE(HttpServletRequest request,Page page,HttpSession session) {
		try{
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			String contractID = request.getParameter("contractID");
			String earnestID = request.getParameter("earnestID"); //定金合同编号
			SignContractBO scBO = new SignContractBO();

			if(earnestID != null && !"".equals(earnestID)){ //若存在定金合同，将定金合同买方信息复制到合同买方信息中
				//BuyerInfoVO buyerInfoVO = new BuyerInfoVO();
				SellerInfoVO sellerInfoVO = new SellerInfoVO();
				List<MetaFilter> params = new ArrayList<MetaFilter>();
				params.add(new MetaFilter("contractID", "=", earnestID));
				List<MetaOrder> orders = new ArrayList<MetaOrder>();
				orders.add(new MetaOrder("serial", MetaOrder.ASC));
				List<SellerInfoVO> sellerList = scBO.search(SellerInfoVO.class, params, orders, page);
				if(sellerList != null && sellerList.size() > 0){
					for(SellerInfoVO bvo : sellerList){
						sellerInfoVO.setContractID(Long.parseLong(contractID));
						sellerInfoVO.setCompID(bvo.getCompID());
						sellerInfoVO.setSerial(bvo.getSerial());
						sellerInfoVO.setSellerName(bvo.getSellerName());
						sellerInfoVO.setSellerBizcardcode(bvo.getSellerBizcardcode());
						sellerInfoVO.setSellerAddress(bvo.getSellerAddress());
						sellerInfoVO.setSellerPostcode(bvo.getSellerPostcode());
						sellerInfoVO.setSellerDelegate(bvo.getSellerDelegate());
						sellerInfoVO.setSellerDlgCall(bvo.getSellerDlgCall());
						sellerInfoVO.setSellerProxy(bvo.getSellerProxy());
						sellerInfoVO.setSellerProxyCall(bvo.getSellerProxyCall());
						sellerInfoVO.setSellerCertcode(bvo.getSellerCertcode());
						scBO.add(sellerInfoVO);
					}
				}
				ContractCancelPwdVO contractCancelPwdVO = new ContractCancelPwdVO();
				List<MetaFilter> params1 = new ArrayList<MetaFilter>();
				params1.add(new MetaFilter("contractID", "=", earnestID));
				List<MetaOrder> orders1 = new ArrayList<MetaOrder>();
				orders1.add(new MetaOrder("serial", MetaOrder.ASC));
				List<ContractCancelPwdVO> pwdList = scBO.search(ContractCancelPwdVO.class, params1, orders1, null);
				if(pwdList != null && pwdList.size() > 0){
					for(ContractCancelPwdVO ccpVo : pwdList){
						contractCancelPwdVO.setContractID(Long.parseLong(contractID));
						contractCancelPwdVO.setSerial(ccpVo.getSerial());
						contractCancelPwdVO.setPwd(ccpVo.getPwd());
						scBO.add(contractCancelPwdVO);
					}
				}

			}

			if(contractID != null && !"0".equals(contractID)){ //存在合同号，查询乙方信息列表

				List<MetaFilter> params = new ArrayList<MetaFilter>();
				params.add(new MetaFilter("contractID", "=", contractID));
				List<MetaOrder> orders = new ArrayList<MetaOrder>();
				orders.add(new MetaOrder("serial", MetaOrder.ASC));
				List<SellerInfoVO> sellerList = scBO.search(SellerInfoVO.class, params, orders, page);
				if(sellerList != null && sellerList.size() > 0){
					List<String> linkparam = new ArrayList<String>();
					linkparam.add("contractid");
					linkparam.add("serial");
					/** 设置table列名 */
					TableProperty tableProperty = new TableProperty();
					tableProperty.setEnableSort(false);
					tableProperty.setRowIndexStauts(false);
					tableProperty.addColumn("顺序", "serial");
					tableProperty.addColumn("让方(甲方)", "buyername", "doModify", linkparam, "buyername", null);
					tableProperty.addColumn("国籍", "buyer_nationality_dict_name");
					tableProperty.addColumn("所在省市", "buyer_province_dict_name");
					tableProperty.addColumn("个人/公司", "buyer_type_dict_name");
					tableProperty.addColumn("性别", "buyer_sex_dict_name");
					tableProperty.addColumn("出生年月日", "buyerbirth", "Date", "yyyy-MM-dd", null);
					tableProperty.addColumn("住所(址)", "buyeraddress");
					tableProperty.addColumn("邮编", "buyerpostcode");
					tableProperty.addColumn("证件名称", "buyer_cardname_dict_name");
					tableProperty.addColumn("号码", "buyercardcode");
					tableProperty.addColumn("联系电话", "buyercall");
					//tableProperty.addColumn("委托/法定代理人", "buyerproxy");
					//tableProperty.addColumn("住所(址)", "buyerproxyadr");
					//tableProperty.addColumn("联系电话", "buyerproxycall");
					linkparam.add("buyername");
					tableProperty.addColumn("删除", "删除", "doDelete", linkparam, "删除", null);
					String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, sellerList, page, contractID);
					request.setAttribute("htmlView", htmlView);
				}
			}
			return "/fhhouse/salecontract/signcontract/BuyerInfo1";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}


	/**
	 * 功能描述：转到新增//或修改乙方信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/operaBuyerInfoFHEAdd")
	public String operaBuyerInfoFHEAdd(HttpServletRequest request) {
		try{
			String contractID = request.getParameter("contractID");
			String serial = request.getParameter("serial");
			SignContractBO scBO = new SignContractBO();
			if(contractID != null && !"".equals(contractID) && serial != null && !"".equals(serial)){ //存在合同编号、乙方顺序，查询乙方信息
				BuyerInfoVO biVo = new BuyerInfoVO();
				biVo.setContractID(Long.parseLong(contractID));
				biVo.setSerial(Integer.parseInt(serial));
				biVo = (BuyerInfoVO) scBO.find(biVo);
				request.setAttribute("buyer", biVo);

				ContractCancelPwdVO ccpVo = new ContractCancelPwdVO();
				ccpVo.setContractID(Long.parseLong(contractID));
				ccpVo.setSerial(Integer.parseInt(serial));
				ccpVo = (ContractCancelPwdVO) scBO.find(ccpVo);
				if(ccpVo != null){
					request.setAttribute("passwd", ccpVo.getPwd());
				}
			}
			return "/fhe/salecontract/signcontract/BuyerInfoSave";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}
    
	/**
	 * 功能描述：保存乙方信息
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/saveBuyerInfoFHE")
    @ResponseBody
	public Map<String , Object> saveBuyerInfoFHE(HttpServletRequest request,HttpSession session) {
		long contractID = Long.valueOf(request.getParameter("contractID"));
		String buyerName = request.getParameter("buyerName");
		String buyerSex = request.getParameter("buyerSex");
		String buyerAddress = request.getParameter("buyerAddress");
		String buyerPostcode = request.getParameter("buyerPostcode");
		String buyerCall = request.getParameter("buyerCall");

		BuyerInfoVOFHE biVo = new BuyerInfoVOFHE();
		biVo.setContractID(contractID);
		biVo.setBuyerName(buyerName);
		biVo.setBuyerSex(buyerSex);;
		biVo.setBuyerAddress(buyerAddress);;
		biVo.setBuyerPostcode(buyerPostcode);;
		biVo.setBuyerCall(buyerCall);;
        JSONArray json = new JSONArray();
        Map<String , Object> map = new HashMap<String , Object>();
		try{
			// String passwd = request.getParameter("passwd");
            SignContractBO scBO = new SignContractBO();
            SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			biVo.setSerial(1);

            boolean res = false;
            if(contractID > 0){
				//乙方撤销密码  ContractCancelPwdVO ccpVo = new ContractCancelPwdVO();

				//乙方信息
                if("-99".equals(biVo.getBuyerSex())){
                    biVo.setBuyerSex(null);
                }

				//if(serial != 0){ //存在乙方顺序，则对该顺序更新，否则新增
				List<MetaFilter> params = new ArrayList<MetaFilter>();
				params.add(new MetaFilter("contractID", "=", contractID));
				List<MetaOrder> orders = new ArrayList<MetaOrder>();
				orders.add(new MetaOrder("serial", MetaOrder.DESC));
				List<BuyerInfoVOFHE> buyerList = scBO.search(BuyerInfoVOFHE.class, params, orders, null); //查询乙方信息，根据乙方顺序降序排列
				int serialnext = 1; //默认乙方顺序的值从1开始
				if(buyerList != null && buyerList.size() > 0){
					request.getSession().setAttribute("buyerList", buyerList);
					BuyerInfoVOFHE buyerInfo = buyerList.get(0); //获取乙方顺序最大的一条记录
					serialnext = buyerInfo.getSerial() + 1; //获取下一条乙方顺序的值
				}

				biVo.setSerial(serialnext);

				res = scBO.saveBuyerInfoFHE(biVo, 1);
				
            }

            if(res == true){
                map.put("result", "success");
				map.put("msg", "乙方保存成功！");
            }else{
                map.put("result", "fail");
				map.put("msg", "乙方保存失败！");
            }
            json = JSONArray.fromObject(map);
			return map;
        }catch (Exception e){
            e.printStackTrace();
            map.put("result", "fail");
			map.put("msg", "乙方保存出现错误！");
            json = JSONArray.fromObject(map);
			return map;
        }
    }

    /**
	 * 功能描述：删除乙方
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/deleteBuyerFHE2")
    @ResponseBody
	public JSONArray deleteBuyerFHE2(HttpServletRequest request) {
        JSONArray json = new JSONArray();
        Map<String , Object> map = new HashMap<String , Object>();
        try{
			String contractID = request.getParameter("contractID");
			String id = request.getParameter("serial");
            SignContractBO scBO = new SignContractBO();

			if(contractID != null && !"".equals(contractID)){
				BuyerInfoVOFHE biVo = new BuyerInfoVOFHE();
				boolean res = true;
				biVo.setContractID(Long.parseLong(contractID));
				biVo.setSerial(Integer.parseInt(id));
				res = scBO.deleteBuyerInfoFHE(biVo);
				if(res == true){
					map.put("result", "success");
					map.put("msg", "乙方删除成功！");
				}else{
					map.put("result", "fail");
					map.put("msg", "乙方删除失败！");
				}
			}else{
				map.put("result", "fail");
				map.put("msg", "未找到对应乙方信息！");
			}
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "fail");
			map.put("message", "乙方删除错误！");
			json = JSONArray.fromObject(map);
			return json;
		}
	}


	/**
	 * 功能描述：删除乙方
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/deleteBuyerFHE")
	@ResponseBody
	public JSONArray deleteBuyerFHE(HttpServletRequest request,@RequestParam(value = "chk_value[]") Integer[] chk_value) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			String contractID = request.getParameter("contractID");
			//String serial = request.getParameter("serial");
			SignContractBO scBO = new SignContractBO();

			if(contractID != null && !"".equals(contractID) && chk_value != null){
				BuyerInfoVOFHE biVo = new BuyerInfoVOFHE();
				boolean res = true;
				for(int i = 0; i < chk_value.length; i++){
					Integer serial = chk_value[i];
					biVo.setContractID(Long.parseLong(contractID));
					biVo.setSerial(serial);

					res = scBO.deleteBuyerInfoFHE(biVo);
					if(res == true){
						map.put("result", "success");
						map.put("msg1", "乙方删除成功！");
					}else{
						map.put("result", "fail");
						map.put("msg", "乙方删除失败！");
					}
				}
				//map.get()
				map.put("length", "" + chk_value.length);
				/*
				 * if(res == true){ map.put("result", "success"); map.put("msg", "乙方删除成功！"); }else{ map.put("result", "fail"); map.put("msg", "乙方删除失败！"); }
				 */
			}else{
				map.put("result", "fail");
				map.put("msg", "未找到对应乙方信息！");
			}
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "fail");
			map.put("message", "乙方删除错误！");
			json = JSONArray.fromObject(map);
			return json;
		}
	}

	/**
	 * 功能描述：合同签约主体
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/gotoSignContractFHE")
	public String gotoSignContract(HttpServletRequest request,HttpSession session) throws Exception {
		SignContractBO scBO = new SignContractBO();
		SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
		RealestateBO rBo = new RealestateBO();
		String houseID = request.getParameter("houseID");
		String startID = request.getParameter("startID");
		String projectID = request.getParameter("projectID");
		String contractIDStr = request.getParameter("contractID");
		String showFlag = request.getParameter("showFlag");
		Long contractID = Long.parseLong(contractIDStr);
		request.setAttribute("showFlag", showFlag);
		String type = "2";
		String url = "";

        try{
			/* 合同信息 */
			ContractDealFHEVO cdVO = new ContractDealFHEVO();
			cdVO.setContractID(contractID);
			cdVO = (ContractDealFHEVO) scBO.find(cdVO);
			request.setAttribute("cdVO", cdVO);

			/* 乙方信息 */
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("contractID", "=", contractID));
			List<MetaOrder> orders = new ArrayList<MetaOrder>();
			orders.add(new MetaOrder("serial", MetaOrder.ASC));
			List<BuyerInfoVO> buyerList = scBO.search(BuyerInfoVO.class, params, orders, null);
			request.getSession().setAttribute("buyerList", buyerList);

			/* 甲方信息 */
			List<MetaFilter> params2 = new ArrayList<MetaFilter>();
			params2.add(new MetaFilter("contractID", "=", contractID));
			List<MetaOrder> orders2 = new ArrayList<MetaOrder>();
			orders2.add(new MetaOrder("serial", MetaOrder.ASC));
			List<SellerInfoVO> sellerList = scBO.search(SellerInfoVO.class, params2, orders2, null);
			request.setAttribute("sellerInfoVO", sellerList.get(0));


			request.setAttribute("contractID", contractID);
			request.setAttribute("houseID", houseID);
			request.setAttribute("projectID", projectID);
			request.setAttribute("startID", startID);
			if("2".equals(type)){
				url = "/fhe/salecontract/signcontract/SellContractSign";
            }
			return url;
        }catch (Exception e){
            e.printStackTrace();
            return ERROR_System;
        }
    }





	/**
	 * 功能描述：草签合同列表跳转FHE
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/queryEditContractFHE")
	public String queryEditContractFHE(HttpServletRequest request,HttpSession session) {
		try{
			request.setAttribute("cmd", "edit");
			session.setAttribute("cmd", "edit");

			Page page = null;
			SignContractBO scBO = new SignContractBO();
			String cmd = "edit";
			String contractID = request.getParameter("contractID");
			String type = "2";
			String projectName = request.getParameter("projectName");
			String buyer = request.getParameter("buyer");

			request.setAttribute("cmd", cmd);
			request.setAttribute("contractID", contractID);
			request.setAttribute("type", type);
			request.setAttribute("projectName", projectName);
			request.setAttribute("buyer", buyer);

			return "/fhe/salecontract/signcontract/EditContractQuery";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

	/**
	 * 功能描述：草签(待签)合同列表
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping("/queryEditContractListFHE")
	@ResponseBody
	public Map<String , Object> queryEditContractListFHE(HttpServletRequest request,HttpSession session) {
		int currentPage = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("limit"));
		JSONArray json = new JSONArray();
		try{
			request.setAttribute("cmd", "edit");
			session.setAttribute("cmd", "edit");
			Map<String , Object> result = new HashMap<String , Object>();
			Page page = new Page(currentPage,pageSize);
			SignContractBO scBO = new SignContractBO();
			String cmd = "edit";

			String contractID = request.getParameter("contractID");
			String projectID = request.getParameter("projectID");
			String sellerName = request.getParameter("sellerName");
			String buyerName = request.getParameter("buyerName");


			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			//List<Object> districtList = this.getUserDistricts(vo.getRegionId());//获取用户操作区县列表
			List<ContractDealFHEVO> list = scBO.queryEditContractFHE(vo.getUserId(), cmd, page, contractID, sellerName, projectID, buyerName);
			//List<ContractDealFHEVO> list = scBO.queryContractFHE("2", cmd, page, contractID, type, projectName, buyer);

			result.put("code", 0);
			result.put("msg", "");
			result.put("count", list.size());
			result.put("data", list);
			request.setAttribute("cmd", cmd);
			request.setAttribute("contractID", contractID);
			request.setAttribute("projectID", projectID);
			request.setAttribute("buyerName", buyerName);
			request.setAttribute("sellerName", sellerName);
			//return "/fhhouse/salecontract/signcontract/EditContractQuery";
			//page.setRecordCount(list.size());
			page.setPageSize(list.size());

			return LayuiDTO.createTableDTO(list, page);
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * 功能描述：已签合同列表跳转FHE
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/queryContractFHE")
	public String queryContractFHE(HttpServletRequest request,HttpSession session) {
		try{
			request.setAttribute("cmd", "signed");
			session.setAttribute("cmd", "signed");

			Page page = null;
			SignContractBO scBO = new SignContractBO();
			String cmd = "edit";
			String contractID = request.getParameter("contractID");
			String type = "2";
			String projectName = request.getParameter("projectName");
			String buyer = request.getParameter("buyer");

			request.setAttribute("cmd", cmd);
			request.setAttribute("contractID", contractID);
			request.setAttribute("type", type);
			request.setAttribute("projectName", projectName);
			request.setAttribute("buyer", buyer);

			return "/fhe/salecontract/signcontract/ContractQuery";
		}catch (Exception e){
			e.printStackTrace();
			return ERROR_System;
		}
	}

    /**
	 * 功能描述：已签合同列表FHE
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/queryContractListFHE")
	@ResponseBody
	public Map<String , Object> queryContractListFHE(HttpServletRequest request,HttpSession session) {
		int currentPage = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("limit"));
		JSONArray json = new JSONArray();
		try{
			request.setAttribute("cmd", "signed");
			session.setAttribute("cmd", "signed");
			Map<String , Object> result = new HashMap<String , Object>();
			Page page = new Page(currentPage, pageSize);
			SignContractBO scBO = new SignContractBO();
			String cmd = "edit";
			String contractID = request.getParameter("contractID");
			String type = "2";
			String projectName = request.getParameter("projectName");
			String buyer = request.getParameter("buyer");


			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			//List<Object> districtList = this.getUserDistricts(vo.getRegionId());//获取用户操作区县列表
			List<ContractDealFHEVO> list = scBO.queryContractFHE("2", cmd, page, contractID, type, projectName, buyer);

			result.put("code", 0);
			result.put("msg", "");
			result.put("count", list.size());
			result.put("data", list);
			request.setAttribute("cmd", cmd);
			request.setAttribute("contractID", contractID);
			request.setAttribute("type", type);
			request.setAttribute("projectName", projectName);
			request.setAttribute("buyer", buyer);
			page.setPageSize(list.size());

			return LayuiDTO.createTableDTO(list, page);
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * 功能描述：草签(待签)合同列表
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 */
    @RequestMapping("/queryEditContractList")
    public String queryEditContractList(HttpServletRequest request,HttpSession session,Page page) {
        try{
            SignContractBO scBO = new SignContractBO();
            String cmd = request.getParameter("cmd");
            String contractID = request.getParameter("contractID");
            String type = request.getParameter("type");
            String projectName = request.getParameter("projectName");
            String buyer = request.getParameter("buyer");
            String startDate = request.getParameter("startDate");
            String overDate = request.getParameter("overDate");
            String startCode = request.getParameter("startCode");
            String district = request.getParameter("district");
            String road = request.getParameter("road");
            String alley = request.getParameter("alley");
            String buildingNumber = request.getParameter("buildingNumber");
            String cell = request.getParameter("cell");

            SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			//List<Object> districtList = this.getUserDistricts(vo.getRegionId());//获取用户操作区县列表
            List<ContractDealVO> list = scBO.queryEditContract(vo.getUserId(), cmd, page, contractID, type, projectName, buyer, startDate, overDate, startCode, district, road, alley, buildingNumber, cell);
            List<String> linkparam = new ArrayList<String>();
            linkparam.add("contractID");
            linkparam.add("type");
            linkparam.add("contractversion");
            linkparam.add("status");
            List<String> linkparam1 = new ArrayList<String>();
            linkparam1.add("contractID");
            linkparam1.add("type");
            linkparam1.add("houseID");
			/** 设置table列名 */
            TableProperty tableProperty = new TableProperty();
            tableProperty.setEnableSort(false);
            tableProperty.setRowIndexStauts(true);
			tableProperty.addColumn("合同号", "contractID", "doContractEdit", linkparam);
			tableProperty.addColumn("合同类型", "type_dict_name");
			tableProperty.addColumn("坐落部位", "location");
			tableProperty.addColumn("买方", "buyerName");
            if("waiting".equals(cmd)){
				tableProperty.addColumn("初签人", "crepersonname");
            }
			tableProperty.addColumn("初签时间", "signDate1", "Date", "yyyy-MM-dd HH:mm:ss", null);
			tableProperty.addColumn("预定天数", "waitday");

            if("edit".equals(cmd)){
				tableProperty.addColumn("限购信息", "限购信息", "xgInfo", linkparam1, "限购信息", null);
				tableProperty.addColumn("删除", "删除", "delContract", linkparam, "删除", null);
            }
			tableProperty.addColumn("打印预览", "打印预览", "viewPrint", linkparam, "打印预览", null);
			tableProperty.addColumn("合同ID", "contractID", true);

            String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);

            request.setAttribute("cmd", cmd);
            request.setAttribute("contractID", contractID);
            request.setAttribute("type", type);
            request.setAttribute("projectName", projectName);
            request.setAttribute("buyer", buyer);
            request.setAttribute("startDate", startDate);
            request.setAttribute("overDate", overDate);
            request.setAttribute("startCode", startCode);
            request.setAttribute("district", district);
            request.setAttribute("road", road);
            request.setAttribute("alley", alley);
            request.setAttribute("buildingNumber", buildingNumber);
            request.setAttribute("cell", cell);
            request.setAttribute("htmlView", htmlView);

            return "/fhhouse/salecontract/signcontract/EditContractQueryList";
        }catch (Exception e){
            e.printStackTrace();
            return ERROR_System;
        }
    }
    
	/**
	 * 功能描述：合同删除FHE
	 * @param request
	 * @return
	 */
	@RequestMapping("/delContractIDFHE")
	@ResponseBody
	public JSONArray delContractIDFHE(HttpServletRequest request) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		String contractID = request.getParameter("contractID");
		try{
			SignContractBO scBO = new SignContractBO();
			boolean result = scBO.delContractFHE(contractID);
			if(result == true){
				map.put("result", "success");
				map.put("message", "合同删除成功！");
			}else{
				map.put("result", "fail");
				map.put("message", "未删除合同记录！");
			}
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "fail");
			map.put("message", "合同删除失败");
			json = JSONArray.fromObject(map);
			return json;
		}
	}

	/**
	 * 功能描述：草签合同删除
	 * @param request
	 * @return
	 */
    @RequestMapping("/delContractID")
    @ResponseBody
    public JSONArray delContractID(HttpServletRequest request) {
        JSONArray json = new JSONArray();
        Map<String , Object> map = new HashMap<String , Object>();
        String contractID = request.getParameter("contractID");
        String type = request.getParameter("type");
        try{
            SignContractBO scBO = new SignContractBO();
            boolean result = scBO.delContract(contractID, type);
            if(result == true){
                map.put("result", "success");
				map.put("message", "合同删除成功！");
            }else{
                map.put("result", "fail");
				map.put("message", "未删除合同记录！");
            }
            json = JSONArray.fromObject(map);
            return json;
        }catch (Exception e){
            e.printStackTrace();
            map.put("result", "fail");
			map.put("message", "合同删除失败");
            json = JSONArray.fromObject(map);
            return json;
        }
    }

    /**
	 * 功能描述：待签合同
	 * @param request
	 * @param session
	 * @return
	 */
    @RequestMapping("/queryWaitingConfirmContract")
    public String queryWaitingConfirmContract(HttpServletRequest request,HttpSession session) {
        try{
            request.setAttribute("cmd", "waiting");
            session.setAttribute("cmd", "waiting");
            return "/fhhouse/salecontract/signcontract/EditContractQuery";
        }catch (Exception e){
            e.printStackTrace();
            return ERROR_System;
        }
    }


	/**
	 * 功能描述：已签合同列表
	 * @param request
	 * @param session
	 * @param page
	 * @return
	 */
    @RequestMapping("/queryContractList")
    public String queryContractList(HttpServletRequest request,HttpSession session,Page page) {
        try{
            SignContractBO scBO = new SignContractBO();
            String contractID = request.getParameter("contractID");
            String type = request.getParameter("type");
            String projectName = request.getParameter("projectName");
            String buyer = request.getParameter("buyer");
            String startDate = request.getParameter("startDate");
            String overDate = request.getParameter("overDate");
            String startCode = request.getParameter("startCode");
            String district = request.getParameter("district");
            String road = request.getParameter("road");
            String alley = request.getParameter("alley");
            String buildingNumber = request.getParameter("buildingNumber");
            String cell = request.getParameter("cell");
            String status = request.getParameter("status");

            SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
            List<ContractDealVO> list = scBO.queryContract(status, vo.getUserId(), page, contractID, type, projectName, buyer, startDate, overDate, startCode, district, road, alley, buildingNumber, cell);
            List<String> linkparam = new ArrayList<String>();
            linkparam.add("contractID");
            linkparam.add("type");
            List<String> linkparam1 = new ArrayList<String>();
            linkparam1.add("contractID");
			/** 设置table列名 */
            TableProperty tableProperty = new TableProperty();
            tableProperty.setEnableSort(false);
            tableProperty.setRowIndexStauts(true);
            tableProperty.addSelectControl("radio", "selContractID", linkparam1, "");
			tableProperty.addColumn("合同号", "contractID");
			tableProperty.addColumn("合同类型", "type_dict_name");
			tableProperty.addColumn("坐落部位", "location");
			tableProperty.addColumn("买方", "buyerName");
			tableProperty.addColumn("面积", "area");
			tableProperty.addColumn("金额", "money");
			tableProperty.addColumn("确认时间", "confirmDate1");
			tableProperty.addColumn("剩余有效期天数", "waitday");
			tableProperty.addColumn("状态", "status_dict_name");
			//tableProperty.addColumn("打印预览", "打印预览", "viewPrint", linkparam, "打印预览", null);
			tableProperty.addColumn("合同ID", "contractID", true);

            String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, list, page);

            request.setAttribute("contractID", contractID);
            request.setAttribute("type", type);
            request.setAttribute("projectName", projectName);
            request.setAttribute("buyer", buyer);
            request.setAttribute("startDate", startDate);
            request.setAttribute("overDate", overDate);
            request.setAttribute("startCode", startCode);
            request.setAttribute("district", district);
            request.setAttribute("road", road);
            request.setAttribute("alley", alley);
            request.setAttribute("buildingNumber", buildingNumber);
            request.setAttribute("cell", cell);
            request.setAttribute("status", status);
            request.setAttribute("htmlView", htmlView);

            return "/fhhouse/salecontract/signcontract/ContractQueryList";
        }catch (Exception e){
            e.printStackTrace();
            return ERROR_System;
        }
    }

    /**
	 * 功能描述：(草签，待签)合同主体
	 * @param request
	 * @return
	 */
    @RequestMapping(value = "/operateContract")
    public String createContract(HttpServletRequest request) {
        try{
            String type = request.getParameter("type");
            String contractID = request.getParameter("contractID");
            String status = request.getParameter("status");
            request.setAttribute("contractID", contractID);
            request.setAttribute("type", type);
            request.setAttribute("status", status);
            return "/fhhouse/salecontract/signcontract/ContractEdit";
        }catch (Exception e){
            e.printStackTrace();
            return ERROR_System;
        }
    }

    /**
	 * 功能描述：合同部分编辑
	 * @param request
	 * @param session
	 * @return
	 */
    @RequestMapping(value = "/editContract")
    public String editContract(HttpServletRequest request,HttpSession session) {
        try{
            SignContractBO scBO = new SignContractBO();
            String url = "";
            String contractID = request.getParameter("contractID");
            String type = request.getParameter("type");
            ContractDealVO cdvo = (ContractDealVO) scBO.find(ContractDealVO.class, Long.parseLong(contractID));
			/* 甲方信息 */
            List<MetaFilter> params = new ArrayList<MetaFilter>();
            params.add(new MetaFilter("contractID", "=", contractID));
            List<SellerInfoVO> list = scBO.search(SellerInfoVO.class, params);
            if(list != null && list.size() > 0){
                SellerInfoVO svo = (SellerInfoVO) list.get(0);
                request.setAttribute("svo", svo);
            }
			/* 乙方信息 */
            List<MetaFilter> params1 = new ArrayList<MetaFilter>();
            params1.add(new MetaFilter("contractID", "=", contractID));
            List<MetaOrder> orders1 = new ArrayList<MetaOrder>();
            orders1.add(new MetaOrder("serial", MetaOrder.ASC));
            List<BuyerInfoVO> buyerList = scBO.search(BuyerInfoVO.class, params1, orders1, null);
            request.setAttribute("buyerList", buyerList);
			/* 房屋土地状况 */
            Attach4VO attach4vo = (Attach4VO) scBO.find(Attach4VO.class, Long.parseLong(contractID));
            request.setAttribute("attach4vo", attach4vo);
			/* 产权信息 */
            List<Attach4RealVO> arList = scBO.queryAttach4RealInfo(contractID);
            request.setAttribute("arList", arList);
			/* 他项信息 */
            List<Attach4OtherVO> aoList = scBO.queryAttach4OtherInfo(contractID);
            request.setAttribute("aoList", aoList);
			/* 限制信息 */
            List<Attach4LimitVO> alList = scBO.queryAttach4LimitInfo(contractID);
            request.setAttribute("alList", alList);
			/* 租赁信息 */
            List<Attach4HireVO> ahList = scBO.queryAttach4HireInfo(contractID);
            request.setAttribute("ahList", ahList);
			/* 许可证信息 */
            /*
             * List<HouseVO> permitList = scBO.queryPermitInfo(String.valueOf(cdvo.getHouseID())); request.setAttribute("permitList", permitList);
             */
			/* 附件信息 */
            List<MetaFilter> para = new ArrayList<MetaFilter>();
            para.add(new MetaFilter("contractID", "=", contractID));
//			List<MetaOrder> orders = new ArrayList<MetaOrder>();
//			orders.add(new MetaOrder("ID", MetaOrder.ASC));
            List<AttachVO> aList = scBO.search(AttachVO.class, para, null, null);
            Map<String , String> map = new HashMap<String , String>();
            if(aList != null && aList.size() > 0){
                for(AttachVO attachVO : aList){
                    map.put(attachVO.getSerial() + "", attachVO.getContent() == null ? "" : new String(attachVO.getContent()));
                }
            }
            request.setAttribute("attachmap", map);
			/* 限购信息 */
            List<MetaFilter> xgParams = new ArrayList<MetaFilter>();
            xgParams.add(new MetaFilter("contractID", "=", contractID));
            List<XgLimitSaleContractVO> xgList = scBO.search(XgLimitSaleContractVO.class, xgParams);
            request.setAttribute("xgList", xgList);

            if(contractID != null && !"".equals(contractID)){
                List<MetaFilter> paramsa = new ArrayList<MetaFilter>();
                paramsa.add(new MetaFilter("contractID", "=", contractID)); //"201100158822"
                List<MetaOrder> ordersa = new ArrayList<MetaOrder>();
                ordersa.add(new MetaOrder("attach2MoneyID", MetaOrder.ASC));
                List<PresellAttach2MoneyVO> palist = scBO.search(PresellAttach2MoneyVO.class, paramsa, ordersa, null);
                if(palist != null && !palist.isEmpty()){
                    request.setAttribute("palist", palist);
                }
            }

            if("1".equals(type)){
                ContractDetailYsVO pcvo = (ContractDetailYsVO) scBO.find(ContractDetailYsVO.class, Long.parseLong(contractID));
                request.setAttribute("ys", pcvo);
                url = "/fhhouse/salecontract/signcontract/PresellContractEdit";
            }
            if("2".equals(type)){
                ContractDetailCsVO scvo = (ContractDetailCsVO) scBO.find(ContractDetailCsVO.class, Long.parseLong(contractID));
                request.setAttribute("cs", scvo);
                url = "/fhhouse/salecontract/signcontract/SellContractEdit";
            }
            if("3".equals(type)){
                EarnestContractVO ecvo = (EarnestContractVO) scBO.find(EarnestContractVO.class, Long.parseLong(contractID));
                request.setAttribute("ecvo", ecvo);
                request.setAttribute("startDate", ecvo.getN1_12() == null ? "" : ecvo.getN1_12() + "-" + ecvo.getN1_13() + "-" + ecvo.getN1_14());
                request.setAttribute("overDate", DateUtil.parseDateTime3(ecvo.getN4_2()));
                url = "/fhhouse/salecontract/signcontract/EarnestContractEdit";
            }
            request.setAttribute("contractID", contractID);
            request.setAttribute("cdvo", cdvo);
            request.setAttribute("status", cdvo.getStatus());
            return url;
        }catch (Exception e){
            e.printStackTrace();
            return ERROR_System;
        }
    }

    /**
	 * 功能描述：待签合同退回
	 * @param request
	 * @return
	 */
    @RequestMapping("/returnContract")
    public String returnContract(HttpServletRequest request,HttpSession session) {
        try{
            SignContractBO scBO = new SignContractBO();
            String contractID = request.getParameter("contractID");
            ContractDealVO cdvo = (ContractDealVO) scBO.find(ContractDealVO.class, Long.parseLong(contractID));
            cdvo.setStatus(FHConstant.CONTRACT_STATUS_EDIT);
            long count = scBO.update(cdvo);
            if(count <= 0){
				throw new Exception("合同退回失败！");
            }
            return "redirect:/outer/signcontract/queryWaitingConfirmContract";
        }catch (Exception e){
            e.printStackTrace();
            return ERROR_System;
        }
    }



    /**
	 * 功能描述：保存出售合同
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/saveSellContractFHE")
    @ResponseBody
	public JSONArray saveSellContractFHE(HttpServletRequest request,HttpSession session,ContractDealFHEVO cdVo,ContractDetailCsVO scVo,SellerInfoVO siVo) {
		JSONArray json = new JSONArray();
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			HouseVO hvo = null;
			SignContractBO scBO = new SignContractBO();
			//DictionaryBO dicBO = new DictionaryBO();
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
            SignerVO signervo = (SignerVO) scBO.find(SignerVO.class, vo.getUserId());
            String contractid = request.getParameter("contractID");
			//当是草签提交操作时，合同状态变为待签；当是待签提交操作时，合同状态变为已签；
			String cmd = request.getParameter("cmdFHE");
            long i = -1;
            long contractID = Long.parseLong(contractid);
            List<MetaFilter> buyerparams = new ArrayList<MetaFilter>();
            buyerparams.add(new MetaFilter("contractID", "=", contractID));
			List<BuyerInfoVOFHE> buyerlist = scBO.search(BuyerInfoVOFHE.class, buyerparams);

			//更新甲方代理人信息
			List<SellerInfoVO> sellerList = scBO.search(SellerInfoVO.class, buyerparams);

            String sellerProxy = request.getParameter("sellerProxy");
            String sellerProxyCall = request.getParameter("sellerProxyCall");
            SellerInfoVO sellervo = sellerList.get(0);
            sellervo.setSellerProxy(sellerProxy);
            sellervo.setSellerProxyCall(sellerProxyCall);
            sellervo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
            sellervo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
            sellervo.setUpdPerson(vo.getLoginName());
            scBO.update(sellervo);

            if(buyerlist != null && buyerlist.size() > 0){
				//预售合同信息
                scVo.setContractID(contractID);
                scVo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
                scVo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
                scVo.setUpdPerson(vo.getLoginName());

                //ContractDealVO cdVo=(ContractDealVO)scmBo.find(ContractDealVO.class, contractID);
				//合同主表信息
                cdVo.setContractID(contractID);
				cdVo.setSellerName(request.getParameter("sellerName")); //甲方名称
                /*
				 * if(request.getParameter("landUse") != null && !"".equals(request.getParameter("landUse"))){ cdVo.setLandUseCode(Long.parseLong(request.getParameter("landUse")) + 1000); //房屋用途代码 int
				 * landType = dicBO.getHouseLandType(String.valueOf(cdVo.getLandUseCode()));//FHConstant.getLandType(Integer.parseInt(request.getParameter("landUse"))); cdVo.setLandType(landType);
				 * //备案系统房屋用 0:住宅、1:办公、2:商铺、3:其他 }
				 */

                List<MetaFilter> params = new ArrayList<MetaFilter>();
                params.add(new MetaFilter("contractID", "=", contractID));
                List<MetaOrder> orders = new ArrayList<MetaOrder>();
                orders.add(new MetaOrder("serial", MetaOrder.ASC));
				List list = scBO.search(BuyerInfoVOFHE.class, params, orders, null);
                if(list.size() == 1){
					BuyerInfoVOFHE bvo = (BuyerInfoVOFHE) list.get(0);
					cdVo.setBuyerName(bvo.getBuyerName()); //乙方名称
                }
                if(list.size() > 1){
					BuyerInfoVOFHE bvo = (BuyerInfoVOFHE) list.get(0);
					cdVo.setBuyerName(bvo.getBuyerName() + "..."); //乙方名称
                }



                ContractDealVO earnestVO = null;

                if("editSubmit".equals(cmd)){
					cdVo.setStatus(FHConstant.CONTRACT_STATUS_WAIT4SIGN); //待签（编辑完成，待签）
				}
                if("waitingSubmit".equals(cmd)){
					cdVo.setStatus(FHConstant.CONTRACT_STATUS_SIGNED); //已签
					hvo = (HouseVO) scBO.find(HouseVO.class, cdVo.getHouseID());
					hvo.setStatus(FHConstant.HOUSE_STATUS_SIGNED); //房屋已签
					// hvo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
					// hvo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
                    hvo.setUpdPerson(vo.getLoginName());
                }

				//甲方信息
                siVo.setContractID(contractID);
                siVo.setSerial(1);
                siVo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
                siVo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
                siVo.setUpdPerson(vo.getLoginName());

                List<AttachVO> attachList = new ArrayList<AttachVO>();
				//合同附件
                int attachSerial[] = { FHConstant.ATTACH_SERIAL_SFJ2, FHConstant.ATTACH_SERIAL_SFJ3, FHConstant.ATTACH_SERIAL_SFJ4,
                        FHConstant.ATTACH_SERIAL_SFJ5, FHConstant.ATTACH_SERIAL_SFJ6, FHConstant.ATTACH_SERIAL_SFJ9, FHConstant.ATTACH_SERIAL_SFJ10,
                        FHConstant.ATTACH_SERIAL_SFJ11, FHConstant.ATTACH_SERIAL_SFJ12 };
                for(int j = 0; j < attachSerial.length; j++){
                    String atcontent = request.getParameter("content" + attachSerial[j]);
                    if(atcontent == null){
                        atcontent = "";
                    }
                    AttachVO aVo = new AttachVO();
                    aVo.setContractID(contractID);
                    aVo.setSerial(attachSerial[j]);
                    aVo.setContent(atcontent.getBytes());
                    aVo.setCrePerson(vo.getLoginName());
                    aVo.setCreDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
                    aVo.setCreTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
                    aVo.setUpdPerson(vo.getLoginName());
                    aVo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
                    aVo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
                    attachList.add(aVo);
                }


				if("editSubmit".equals(cmd)){
					//i = scBO.saveContract(cdVo, null, scVo, null, siVo, attachList, hvo, null, earnestVO);
					i = scBO.saveContractFHE(cdVo, null, scVo, null, siVo, attachList, hvo, null, earnestVO);
				}
				if("waitingSubmit".equals(cmd)){
					i = scBO.confirmContractFHE(cdVo, null, scVo, null, siVo, attachList, hvo, null, earnestVO);
				}
            }else{
                i = -9999999;
            }

			//			/* 房屋一房一价检查 */
//			HouseBandAverageVO hbaVo = scBO.queryHouseAverage(String.valueOf(cdVo.getHouseID()));
//			ContractDetailCsVO csVO = (ContractDetailCsVO) scBO.find(ContractDetailCsVO.class, contractID);
//			if(csVO != null && csVO.getf0705() != null && !"".equals(csVO.getf0705())){
//				if(Double.parseDouble(csVO.getf0705()) > hbaVo.getNBandAverage()){
//					i = -111;
//				}else{
//					if(Double.parseDouble(csVO.getf0705()) < hbaVo.getNBandAverageXia()){
//						i = -222;
//					}
//				}
//			}
            if(i > 0){
                map.put("result", "success");
				map.put("msg", "出售合同操作成功！");
            }else if(i == -9999999){
                map.put("result", "fail");
				map.put("msg", "请确保合同有乙方信息！");
				//			}else if(i == -111){
				//				map.put("result", "fail");
				//				map.put("msg", "该房屋申报基准价" + String.valueOf(hbaVo.getNHouseAverage()) + " (元/米), 上限单价" + String.valueOf(hbaVo.getNBandAverage()) + "(元/米)！\n每平方米房屋建筑面积单价超过上限！");
				//			}else if(i == -222){
				//				map.put("result", "fail");
				//				map.put("msg", "未在备案价格范围内！每平方米房屋建筑面积单价低于下限！");
            }else{
                map.put("result", "fail");
				map.put("msg", "出售合同操作失败！");
            }

            json = JSONArray.fromObject(map);
            return json;
        }catch (Exception e){
            e.printStackTrace();
            map.put("result", "fail");
			map.put("msg", "出售合同操作出错！");
            json = JSONArray.fromObject(map);
            return json;
        }
    }
    


    
    
    
    	/**
	 * 功能描述：合同确认检查
	 * @param request
	 * @return
	 */
    @RequestMapping(value = "/confirmCheck")
    @ResponseBody
    public JSONArray contractConfirmCheck(HttpServletRequest request,HttpSession session) {
        JSONArray json = new JSONArray();
        Map<String , Object> map = new HashMap<String , Object>();
        try{
            String contractID = request.getParameter("contractID");
            String startId = request.getParameter("startID");
            String houseId = request.getParameter("houseID");
            String type = request.getParameter("type");
            SmUserVO uservo = (SmUserVO) session.getAttribute("LgUser");
            String signerId = String.valueOf(uservo.getUserId());

            SignContractBO sBo = new SignContractBO();

			/** 2018-12-05 added by gcf 新增检查房屋抵押。 **/
            String houseID = request.getParameter("houseID");
            List<HouseVO> houseList = sBo.checkOtherRightByHouseID(houseID);
            if(houseList != null && houseList.size() > 0){
                map.put("result", "fail");
				map.put("msg", "该房屋存在抵押，请撤销抵押后继续网签！");
                json = JSONArray.fromObject(map);
                return json;
            }
			/** 2018-12-05 added by gcf 新增检查房屋抵押。 **/


            List<MetaFilter> params = new ArrayList<MetaFilter>();
            params.add(new MetaFilter("contractID", "=", contractID));
            List<MetaOrder> orders = new ArrayList<MetaOrder>();
            orders.add(new MetaOrder("serial", MetaOrder.ASC));
            List<BuyerInfoVO> buyerList = sBo.search(BuyerInfoVO.class, params, orders, null);
            List<ContractCancelPwdVO> ccPwdList = sBo.search(ContractCancelPwdVO.class, params, orders, null);
            List<SellerInfoVO> sellerList = sBo.search(SellerInfoVO.class, params, orders, null);

            if(buyerList != null && !buyerList.isEmpty()){
            	//if(1!=1){
				/*** 20181120 added by gcf 新增检查住保中间库数据，如果有数据，则不能保存乙方数据-start ****/
                ZBReader reader = new ZBReader();
                for(BuyerInfoVO bvo : buyerList){
                    HashMap resultMap = reader.getToken(Constant.username, Constant.password);
                    if(resultMap != null){
						if("1".equals(resultMap.get("result").toString())){//有数据
							//获取token
                            String token = resultMap.get("token").toString();
							//根据姓名证件号，获取住保中间库信息
							resultMap = reader.getZBHouseInfo(bvo.getBuyerName(), bvo.getBuyerCardcode(), token);
                            if(resultMap != null){
                                if("1".equals(resultMap.get("result").toString())){
                                    String data = resultMap.get("data").toString();
                                    String arr[] = data.split(",");
                                    if(arr != null && arr.length > 0){
                                        String distName = sBo.getDistNameForZB(arr[0].substring(0, 2).toUpperCase());
                                        map.put("result", "fail");
										map.put("msg", "您好，该证件号码" + bvo.getBuyerCardcode() + "存在于受理序号为" + arr[0] + "的住房保障受理卷中，请先到" + distName + "户口所在住房保障中心取消住房保障申请。");
                                        return JSONArray.fromObject(map);
                                    }
                                }
                            }else{
                                map.put("result", "fail");
								map.put("msg", "获取住保接口中间库数据失败！");
                                return JSONArray.fromObject(map);
                            }
                        }
                    }else{
                        map.put("result", "fail");
						map.put("msg", "获取住保接口令牌失败！");
                        return JSONArray.fromObject(map);
                    }
                }
            	//}
            }
            String msg=null;
            if(buyerList != null && buyerList.size() > 0 && ccPwdList != null && buyerList.size() == ccPwdList.size()){
            }else{
				msg = "乙方信息与乙方撤销密码信息不符！";
            }

            RealestateBO rBo = new RealestateBO();
            if(msg==null){
                CHFlatVO cfvo = (CHFlatVO) rBo.find(CHFlatVO.class, Long.parseLong(houseId));
                if(cfvo == null){
					msg = "不存在该房屋，不能签约！";
                }
            }
            if(msg==null){
                int startResult = sBo.checkHouseExist(startId, houseId);
                if(startResult <= 0){
					msg = "该房屋已不属于本开盘单元，不能签约！";
                }
            }
            if(msg==null){
                int signerResult = sBo.checkSignerHouseRelate(signerId, houseId);
                if(signerResult <= 0){
					msg = "该房屋所属楼幢已不属于本签约人，不能签约！";
                }
            }
            if(msg==null){
                int limitResult = rBo.checkHouseLimit(houseId);
                if(limitResult > 0){
					msg = "此房屋已被查封，不能签约！";
                }
            }
            if(msg==null){
                int disscentResult = rBo.checkHouseDisscent(houseId);
                if(disscentResult > 0){
					msg = "此房屋已被异议，不能签约！";
                }
            }
            if(msg==null){
                HouseVO hvo = (HouseVO) sBo.find(HouseVO.class, Long.parseLong(houseId));
                if(! (hvo.getStatus() == 4 || hvo.getStatus() == 8)){
					msg = "此房屋不可售，不能签约！";
                }
            }
            if(msg==null){
                String earnestID = sBo.queryEarnestID(contractID, type);
                List<Integer> statusList = new ArrayList<Integer>();
                statusList.add(FHConstant.CONTRACT_STATUS_SIGNED);
                statusList.add(FHConstant.CONTRACT_STATUS_REGISTED);
                statusList.add(FHConstant.CONTRACT_STATUS_ACCEPTED);
                statusList.add(FHConstant.CONTRACT_STATUS_CERTIFICATED);
                List<MetaFilter> params1 = new ArrayList<MetaFilter>();
                if(earnestID != null && !"".equals(earnestID)){
                    params1.add(new MetaFilter("contractID", "<>", earnestID));
                }
                params1.add(new MetaFilter("houseID", "=", houseId));
                params1.add(new MetaFilter("status", "in", statusList));
                List<ContractDealVO> list = sBo.search(ContractDealVO.class, params1);
                if(list != null && list.size() > 0){
					msg = "该房屋已有“已签”或“已登记”或“已领证”或“已受理”的合同!";
                }
            }
//			if(msg == null){
//				double maxPrice = Double.parseDouble(sBo.checkHousePrice(contractID));
//				double unitPrice = 0.0;
//				String totalPrice = "0";
//				String area = request.getParameter("f0308");
//				if(area != null && !"".equals(area) && Double.parseDouble(area) > 0){
//					if("1".equals(type)){
//						String f0601 = request.getParameter("f0601");
//						if(f0601 != null && "1".equals(f0601)){
//							totalPrice = request.getParameter("f0603");
//						}else if(f0601 != null && "2".equals(f0601)){
//							totalPrice = request.getParameter("f0606");
//						}else if(f0601 != null && "3".equals(f0601)){
//							totalPrice = request.getParameter("f0610");
//						}
//					}
//					if("2".equals(type)){
//						String f0701 = request.getParameter("f0701");
//						if(f0701 != null && "1".equals(f0701)){
//							totalPrice = request.getParameter("f0703");
//						}else if(f0701 != null && "2".equals(f0701)){
//							totalPrice = request.getParameter("f0706");
//						}else if(f0701 != null && "3".equals(f0701)){
//							totalPrice = request.getParameter("f0710");
//						}
//					}
//					if(totalPrice != null && !"".equals(totalPrice)){
//						unitPrice = Double.parseDouble(totalPrice) / Double.parseDouble(area);
//					}else{
			//						msg = "总价不能为空！";
//					}
//				}else{
			//					msg = "建筑面积不能为空！";
//				}
//				if(maxPrice < unitPrice){
			//					msg = "合同单价是否大于设置的房屋价格，不能签约！";
//				}
//			}

			//房屋一房一价检查 
            if(msg == null){
                HouseBandAverageVO hbaVo = sBo.queryHouseAverage(String.valueOf(houseId));
                if(hbaVo != null){
                    boolean checkFlag = true;
                    String unitPrice1 = "";
                    if("1".equals(type)){
                        String f0601 = request.getParameter("f0601");
                        if(f0601 != null && "1".equals(f0601)){
                            unitPrice1 = request.getParameter("f0602");
                        }else if(f0601 != null && "2".equals(f0601)){
                            unitPrice1 = request.getParameter("f0605");
                        }else if(f0601 != null && "5".equals(f0601)){
                            unitPrice1 = request.getParameter("f0629");
                        }else if(f0601 != null && "6".equals(f0601)){
                            unitPrice1 = request.getParameter("f0634");
                        }else{
                            checkFlag = false;
                        }
                    }
                    if("2".equals(type)){
                        String f0701 = request.getParameter("f0701");
                        if(f0701 != null && "1".equals(f0701)){
                            unitPrice1 = request.getParameter("f0702");
                        }else if(f0701 != null && "2".equals(f0701)){
                            unitPrice1 = request.getParameter("f0705");
                        }else if(f0701 != null && "5".equals(f0701)){
                            unitPrice1 = request.getParameter("f0729");
                        }else if(f0701 != null && "6".equals(f0701)){
                            unitPrice1 = request.getParameter("f0734");
                        }else{
                            checkFlag = false;
                        }
                    }
                    if(checkFlag){
                        if(unitPrice1 != null && !"".equals(unitPrice1) && Double.parseDouble(unitPrice1) > hbaVo.getNBandAverage()
                                || Double.parseDouble(unitPrice1) < hbaVo.getNBandAverageXia()){
							msg = "该房屋申报基准价" + String.valueOf(hbaVo.getNHouseAverage()) + " (元/米), 上限单价" + String.valueOf(hbaVo.getNBandAverage()) + "(元/米)！\n该合同单价超过上限或低于下限！";
                        }else{
							//						msg = "该合同单价不能为空！";
                        }
                    }
                    if(msg == null){
                        checkFlag = true;
                        double unitPrice = 0.0;
                        String totalPrice = "0";
                        String area = request.getParameter("f0308");
                        if(area != null && !"".equals(area) && Double.parseDouble(area) > 0){
                            if("1".equals(type)){
                                String f0601 = request.getParameter("f0601");
                                if(f0601 != null && "1".equals(f0601)){
                                    totalPrice = request.getParameter("f0603");
                                }else if(f0601 != null && "2".equals(f0601)){
                                    totalPrice = request.getParameter("f0606");
                                }else if(f0601 != null && "3".equals(f0601)){
                                    totalPrice = request.getParameter("f0610");
                                }else if(f0601 != null && "5".equals(f0601)){
                                    totalPrice = request.getParameter("f0631");
                                }else if(f0601 != null && "6".equals(f0601)){
                                    totalPrice = request.getParameter("f0636");
                                }else{
                                    checkFlag = false;
                                }
                            }
                            if("2".equals(type)){
                                String f0701 = request.getParameter("f0701");
                                if(f0701 != null && "1".equals(f0701)){
                                    totalPrice = request.getParameter("f0703");
                                }else if(f0701 != null && "2".equals(f0701)){
                                    totalPrice = request.getParameter("f0706");
                                }else if(f0701 != null && "3".equals(f0701)){
                                    totalPrice = request.getParameter("f0710");
                                }else if(f0701 != null && "5".equals(f0701)){
                                    totalPrice = request.getParameter("f0731");
                                }else if(f0701 != null && "6".equals(f0701)){
                                    totalPrice = request.getParameter("f0736");
                                }else{
                                    checkFlag = false;
                                }
                            }
                            if(checkFlag){
                                if(totalPrice != null && !"".equals(totalPrice) && Double.parseDouble(totalPrice) > 0){
                                    unitPrice = Double.parseDouble(totalPrice) / Double.parseDouble(area);
                                    if( (unitPrice - hbaVo.getNBandAverage()) >= 0.01 || 0.01 <= (hbaVo.getNBandAverageXia() - unitPrice)){
										msg = "该房屋申报基准价" + String.valueOf(hbaVo.getNHouseAverage()) + " (元/米), 上限单价" + String.valueOf(hbaVo.getNBandAverage()) + "(元/米)！\n该合同总价超过上限或低于下限！";
										System.out.println("合同(" + contractID + ")计算单价和基准价格比较不一致，unitPrice=" + unitPrice);
                                    }
                                }else{
									msg = "总价不能为空";
                                }
                            }
                        }else{
							msg = "建筑面积不能为空！";
                        }
                    }
                }
            }

			//房屋维修资金检查
            if(msg==null){
                HouseVO houseVo = (HouseVO) sBo.find(HouseVO.class, Long.parseLong(houseId));
                HMFCityVO hcvo = (HMFCityVO) sBo.find(HMFCityVO.class, houseVo.getDistrictID());
                if(hcvo != null && hcvo.getNstate() == 1){
                    List<HouseVO> housePayList = sBo.queryHousePayInfo(houseId);
                    if(housePayList == null || housePayList.size() <= 0){
						msg = "该房屋专项维修资金系统中没有查询到该房屋，请联系维修资金管理中心.";
                    }else{
                        for(HouseVO housePay : housePayList){
                            if("0".equals(housePay.getAttribute("audit_status").toString())){
								msg = "该房屋没有通过房屋专项维修资金系统的检查(核定应交情况),请联系维修资金管理中心.";
                            }if("1".equals(housePay.getAttribute("repay_flag").toString())){
								msg = "该房屋没有通过房屋专项维修资金系统的检查(需交存维修资金),请联系维修资金管理中心.";
                            }
                        }
                    }
                }
            }

			//合同限购检查
            if(msg == null){
                String xgID = request.getParameter("xgID");
                boolean xgFlag = true;
                
				//2020.3.23 ph 限购合同是否仅仅检查个人买方标志获取
            	long xgManCheckState = sBo.findxgManCheckState(houseId);
				//限购检查买方对象列表
                List<MetaFilter> params2 = new ArrayList<MetaFilter>();
                params2.add(new MetaFilter("contractID", "=", contractID));
				//判断是否仅仅检查个人买方
                if(xgManCheckState==1){
                	params2.add(new MetaFilter("buyerType", "=", 0));
                }
                List<MetaOrder> orders2 = new ArrayList<MetaOrder>();
                orders2.add(new MetaOrder("serial", MetaOrder.ASC));
                List<BuyerInfoVO> xgCBuyerList = sBo.search(BuyerInfoVO.class, params2, orders2, null);
				//判断限购检查乙方是否存在
                if(xgCBuyerList!=null  && xgCBuyerList.size()>0){
					//判断买方是否个人
	                for(BuyerInfoVO bvo : xgCBuyerList){
	                    if(!"0".equals(bvo.getBuyerType())){
	                        xgFlag = false;
	                        break;
	                    }
	                }
	
	                if(xgFlag){
						//判断区县是否限购
	                    long nState = sBo.findState(houseId);
						//判断房屋是否商品房
						CHFlatVO flatvo = new RealestateBO().findCHFlat(Long.parseLong(houseId));
	                    boolean isXG = true;
	                    String houseType = flatvo.getAttribute("house_type") == null ? "" : flatvo.getAttribute("house_type").toString();
	                    if(!"".equals(houseType) && houseType != null){
	                        int ht = Integer.parseInt(houseType);
							//不限购房屋类型
							int notXGType[] = { 1, 3, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14, 15 };
	                        for(int it : notXGType){
	                            if(ht == it){
	                                isXG = false;
	                                break;
	                            }
	                        }
	                    }
	                    HouseVO hvo = (HouseVO) sBo.find(HouseVO.class, Long.parseLong(houseId));
	                    //
	                    //XGHouseRecordVO xghrVo = (XGHouseRecordVO) scBO.find(XGHouseRecordVO.class, Long.parseLong(houseId));
	                    if(nState == 1 && "1".equals(flatvo.getFlat_Type()) && hvo.getNoXgState() == 0 && isXG){
	                        if(xgID == null || "".equals(xgID)){
								msg = "当前房屋为限购房屋，请关联限购令信息！";
	                        }else{
								//检查买方信息是否在限购人员信息中
	                            for(BuyerInfoVO bvo : xgCBuyerList){
	                                List<XgBuyerOwnerNameVO> xgBuyerlist = sBo.queryXgBuyerInfo(xgID, bvo.getBuyerName(), bvo.getBuyerCardcode());
	                                if(xgBuyerlist == null || xgBuyerlist.isEmpty()){
										msg = "合同中乙方姓名、证件号码与购房人及家庭成员情况申报表中的家庭成员姓名不符！";
	                                    break;
	                                }
	                            }
	
	                        }
	                    }else{
	                        if(xgID != null && !"".equals(xgID)){
								msg = "当前房屋为非限购房屋，无需关联限购令信息，请删除已关联的限购令信息！";
	                        }
	                    }
	
	                }else{
	                    if(xgID != null && !"".equals(xgID)){
							msg = "当前合同无需关联限购令信息，请删除已关联的限购令信息！";
	                    }
	                }
                }else{
                	if(xgID != null && !"".equals(xgID)){
						msg = "当前合同无需关联限购令信息，请删除已关联的限购令信息！";
                    }
                }
            }

			//判断付款总额
            if(msg == null && "1".equals(type)){
                List<MetaFilter> paramsa = new ArrayList<MetaFilter>();
                paramsa.add(new MetaFilter("contractID", "=", contractID));
                List<PresellAttach2MoneyVO> palist = sBo.search(PresellAttach2MoneyVO.class, paramsa);
				//合同条款中总价获取
				ContractDetailYsVO ysVO = (ContractDetailYsVO) sBo.find(ContractDetailYsVO.class, Long.parseLong(contractID));
                double cTotalmoney = 0;
                if(ysVO != null){
                    String totalPrice = "0";
                    String decoPrice = "0";
                    if("1".equals(ysVO.getf0601())){
                        totalPrice = ysVO.getf0603();
                        decoPrice = ysVO.getf0619();
                    }else if("2".equals(ysVO.getf0601())){
                        totalPrice = ysVO.getf0606();
                        decoPrice = ysVO.getf0609();
                    }else if("5".equals(ysVO.getf0601())){
                        totalPrice = ysVO.getf0631();
                        //decoPrice = ysVO.getf0609();
                    }else if("6".equals(ysVO.getf0601())){
                        totalPrice = ysVO.getf0636();
                        //decoPrice = ysVO.getf0609();
                    }else{
                        totalPrice = ysVO.getf0610();
                        decoPrice = ysVO.getf0624();
                    }
                    if(totalPrice == null || "".equals(totalPrice)){
                        totalPrice = "0";
                    }
                    if(decoPrice == null || "".equals(decoPrice)){
                        decoPrice = "0";
                    }
                    cTotalmoney = Double.parseDouble(totalPrice) + Double.parseDouble(decoPrice);
                }
                if(palist != null && !palist.isEmpty()){
					//付款计划中的总价
                    double totalmoney = palist.get(0).getTotalMoney();
					//付款计划中的总价与合同条件中总价是否一致
                    if(Math.abs(cTotalmoney - totalmoney) >= 0.01){
						msg = "付款中付款总额与合同条款中房屋总价与装修总价的和不相等，请重新确认！";
                    }else{
                        double money = 0;
                        for(PresellAttach2MoneyVO pa : palist){
                            money += pa.getMoney();
                        }
                        if(Math.abs(money - totalmoney) >= 0.01){
							msg = "付款总额与房屋总价与装修总价的和不相等，请重新确认！";
                        }
                    }
                }else{
					msg = "付款记录为空，请重新确认！";
                }
            }

			//资金监管
            if(msg == null && "1".equals(type)){
                String regulator = request.getParameter("f0725");
                String manageAcctName = request.getParameter("f0726");
                String manageAcct = request.getParameter("f0727");
                if(regulator != null && !"".equals(regulator) && (manageAcctName == null || "".equals(manageAcctName) || manageAcct == null || "".equals(manageAcct))){
					msg = "该账户未纳入资金监管，请重新确认！";
                }
//				HouseVO hvo = (HouseVO) sBo.find(HouseVO.class, Long.parseLong(houseId));
//				if(hvo != null){
//					PresellDistrictidManageVO pdmVo = (PresellDistrictidManageVO) sBo.find(PresellDistrictidManageVO.class, hvo.getDistrictID());
//					PresellManageVO pmVo = (PresellManageVO) sBo.find(PresellManageVO.class, hvo.getBuilding_ID());
//					if(pdmVo != null && pdmVo.getState() == 1){
//						if(pmVo == null || (pmVo.getManage_Acct() == null || "".equals(pmVo.getManage_Acct()) || pmVo.getManage_Acct_Name() == null || "".equals(pmVo.getManage_Acct_Name()))){
				//							msg = "该账户为纳入资金监管，请重新确认！";
//						}
//					}
//				}
            }

            if(msg==null){
                map.put("result", "success");
                map.put("msg", "");
            }else{
                map.put("result", "fail");
                map.put("msg", msg);
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("result", "fail");
			map.put("msg", "合同确认出错，请重试或联系管理员！");
        }
        json = JSONArray.fromObject(map);
        return json;
    }

    /**
	 * 功能描述：定金合同甲方密码验证
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/contractSellerCheckFHE")
    public String contractSellerCheck(HttpServletRequest request,HttpSession session) {
        try{
            SignContractBO scBO = new SignContractBO();
            String projectID = request.getParameter("projectID");
            String houseId = request.getParameter("houseId");
            List<SellerInfoVO> list = scBO.querySellerInfo(houseId);
            if(list != null && list.size() > 0){
                SellerInfoVO svo = (SellerInfoVO) list.get(0);
                request.setAttribute("sellerName", svo.getSellerName());
            }

            List<MetaFilter> params = new ArrayList<MetaFilter>();
            params.add(new MetaFilter("projectID", "=", projectID));
			List<CompCancelPwdVO> compList = scBO.search(CompCancelPwdVO.class, params); //通过项目ID查询合同甲方密码信息
            if(compList != null && compList.size() > 0){
                CompCancelPwdVO cpvo = (CompCancelPwdVO) compList.get(0);
                request.setAttribute("sellerPwd", cpvo.getPwd());
            }
            request.setAttribute("houseId", houseId);
			return "/fhe/salecontract/signcontract/ContractSellerCheck";
        }catch (Exception e){
            e.printStackTrace();
            return ERROR_System;
        }
    }

    /**
	 * 功能描述：定金合同乙方密码验证
	 * @param request
	 * @param session
	 * @return
	 */
    @RequestMapping("/contractBuyerCheck")
    public String contractBuyerCheck(HttpServletRequest request,HttpSession session) {
        try{
            SignContractBO scBO = new SignContractBO();
            String houseId = request.getParameter("houseId");
            List<BuyerInfoVO> list = scBO.queryBuyerInfo(houseId);
            request.setAttribute("list", list);
            List<ContractCancelPwdVO> cpList = scBO.queryBuyerPwd(houseId);
            request.setAttribute("cpList", cpList);

            request.setAttribute("houseId", houseId);
            return "/fhhouse/salecontract/signcontract/ContractBuyerCheck";
        }catch (Exception e){
            e.printStackTrace();
            return ERROR_System;
        }
    }

    /**
	 * 功能描述：附件四编辑
	 * @param request
	 * @param session
	 * @return
	 */
    @RequestMapping("/editAttach4")
    public String editAttach4(HttpServletRequest request,HttpSession session) {
        try{
            SignContractBO scBO = new SignContractBO();
            String contractID = request.getParameter("contractID");
            if(contractID != null && !"".equals(contractID)){
                List<MetaFilter> params = new ArrayList<MetaFilter>();
                params.add(new MetaFilter("contractID", "=", contractID)); //"201100158822"
                List<MetaOrder> orders = new ArrayList<MetaOrder>();
                orders.add(new MetaOrder("attach2MoneyID", MetaOrder.ASC));
                List<PresellAttach2MoneyVO> palist = scBO.search(PresellAttach2MoneyVO.class, params, orders, null);
                PresellAttach2MoneyVO paVo = new PresellAttach2MoneyVO();
                if(palist != null && !palist.isEmpty()){
                    request.setAttribute("palist", palist);
                    paVo = palist.get(0);
                }else{
//					ContractDealVO cdVo = (ContractDealVO) scBO.find(ContractDealVO.class, Long.parseLong(contractID));
                    ContractDetailYsVO ysVO = (ContractDetailYsVO) scBO.find(ContractDetailYsVO.class, Long.parseLong(contractID));
                    if(ysVO != null){
                        String totalPrice = "0";
                        String decoPrice = "0";
                        if("1".equals(ysVO.getf0601())){
                            totalPrice = ysVO.getf0603();
                            decoPrice = ysVO.getf0619();
                        }else if("2".equals(ysVO.getf0601())){
                            totalPrice = ysVO.getf0606();
                            decoPrice = ysVO.getf0609();
                        }else if("5".equals(ysVO.getf0601())){
                            totalPrice = ysVO.getf0631();
                            //decoPrice = ysVO.getf0609();
                        }else if("6".equals(ysVO.getf0601())){
                            totalPrice = ysVO.getf0636();
                            //decoPrice = ysVO.getf0609();
                        }else{
                            totalPrice = ysVO.getf0610();
                            decoPrice = ysVO.getf0624();
                        }
                        if(totalPrice == null || "".equals(totalPrice)){
                            totalPrice = "0";
                        }
                        if(decoPrice == null || "".equals(decoPrice)){
                            decoPrice = "0";
                        }
                        paVo.setTotalMoney(Double.parseDouble(totalPrice) + Double.parseDouble(decoPrice));
                    }
                }
                request.setAttribute("paVo", paVo);
            }
            return "/fhhouse/salecontract/signcontract/PresellAttach2";
        }catch (Exception e){
            e.printStackTrace();
            return ERROR_System;
        }
    }

    /**
	 * 功能描述：附件四新增
	 * @param request
	 * @param session
	 * @return
	 */
    @RequestMapping("/operaAttach2Money")
    public String operaAttach2Money(HttpServletRequest request,HttpSession session,PresellAttach2MoneyVO pa) {
        try{
            SignContractBO scBO = new SignContractBO();
            long contractID = pa.getContractID();
            if(contractID > 0){
                List<MetaFilter> params = new ArrayList<MetaFilter>();
                params.add(new MetaFilter("contractID", "=", contractID));
                List<MetaOrder> orders = new ArrayList<MetaOrder>();
                orders.add(new MetaOrder("stateSign", MetaOrder.DESC));
                List<PresellAttach2MoneyVO> paList = scBO.search(PresellAttach2MoneyVO.class, params, orders, null);
                if(paList != null && !paList.isEmpty()){
                    pa = paList.get(0);
                    pa.setStateSign(pa.getStateSign() + 1);
                    request.setAttribute("pa", pa);
                }else{
                    pa.setStateSign(0);
                    request.setAttribute("pa", pa);
                }
            }
            return "/fhhouse/salecontract/signcontract/PresellAttach2Money";
        }catch (Exception e){
            e.printStackTrace();
            return ERROR_System;
        }
    }

    /**
	 * 功能描述：附件四保存
	 * @param request
	 * @param session
	 * @return
	 */
    @RequestMapping("/saveAttach")
    @ResponseBody
    public JSON saveAttach(HttpServletRequest request,HttpSession session,PresellAttach2MoneyVO pa) {
        Map<String , Object> map = new HashMap<String , Object>();
        try{
            SignContractBO scBO = new SignContractBO();
            if(pa.getContractID() > 0){
                pa.setCreDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
                pa.setCreTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
                pa.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
                pa.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
                List<MetaFilter> params = new ArrayList<MetaFilter>();
                params.add(new MetaFilter("contractID", "=", pa.getContractID()));
                params.add(new MetaFilter("state", "=", pa.getState()));
                List<MetaOrder> orders = new ArrayList<MetaOrder>();
                orders.add(new MetaOrder("stateSign", MetaOrder.DESC));
                List<PresellAttach2MoneyVO> paList = scBO.search(PresellAttach2MoneyVO.class, params, orders, null);
                if(paList != null && !paList.isEmpty()){
                    pa.setStateSign(paList.get(0).getStateSign() + 1);
                }else{
                    pa.setStateSign(0);
                }
                scBO.add(pa);
                map.put("result","success");
				map.put("msg", "合同号为" + pa.getContractID() + "的付款项保存成功！");
            }else{
                map.put("result","fail");
				map.put("msg", "合同编号缺失，请重新编辑！");
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("result", "fail");
			map.put("msg", "付款项保存出错，请重试！");
        }
        return JSONArray.fromObject(map);
    }

    /**
	 * 功能描述：附件四保存
	 * @param request
	 * @param session
	 * @return
	 */
    @RequestMapping("/delattachmoney")
    @ResponseBody
    public JSON delattachmoney(HttpServletRequest request,HttpSession session) {
        Map<String , Object> map = new HashMap<String , Object>();
        try{
            SignContractBO scBO = new SignContractBO();
            String ID = request.getParameter("ID");
            String contractID = request.getParameter("contractID");
            String stateSign = request.getParameter("stateSign");
            if(ID != null && !"".equals(ID)){
                List<MetaFilter> whereFields = new ArrayList<MetaFilter>();
                whereFields.add(new MetaFilter("attach2MoneyID", "=", ID));
                scBO.delete(PresellAttach2MoneyVO.class, whereFields);
                map.put("result", "success");
				map.put("msg", "合同号为" + contractID + "的付款项删除成功！");
            }else{
                map.put("result", "fail");
				map.put("msg", "付款项编号缺失，请重新编辑！");
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("result", "fail");
			map.put("msg", "付款项删除出错，请重试！");
        }
        return JSONArray.fromObject(map);
    }


	@RequestMapping("/signXGCheckFHE")
    @ResponseBody
	public JSON signXGCheckFHE(HttpServletRequest request,HttpSession session) {
        Map<String , Object> map = new HashMap<String , Object>();
        String msg = null;
        try{
            String contractID = request.getParameter("contractID");
			// String houseId = request.getParameter("houseID");

            SignContractBO sBo = new SignContractBO();
            List<MetaFilter> params = new ArrayList<MetaFilter>();
            params.add(new MetaFilter("contractID", "=", contractID));
            List<MetaOrder> orders = new ArrayList<MetaOrder>();
            orders.add(new MetaOrder("serial", MetaOrder.ASC));
			List<BuyerInfoVOFHE> buyerList = sBo.search(BuyerInfoVOFHE.class, params, orders, null);
			if(buyerList == null || buyerList.isEmpty()){
				map.put("result", "fail");
				map.put("msg", "请先添加乙方！！");
				return JSONArray.fromObject(map);
			}

            if(msg == null){
                map.put("result", "success");
            }else{
                map.put("result", "fail");
                map.put("msg", msg);
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("result", "fail");
			map.put("msg", "付款项删除出错，请重试！");
        }
        return JSONArray.fromObject(map);
    }

    /**
	 * 功能描述：房屋抵押检查
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/doQueryCheckOtherFHE")
    @ResponseBody
	public JSONArray doQueryCheckOtherFHE(HttpServletRequest request,HttpSession session) {
        JSONArray json = new JSONArray();
        Map<String , Object> map = new HashMap<String , Object>();
        SignContractBO scBO = new SignContractBO();
        try{
            String houseID = request.getParameter("houseID");
			List<HouseVO> list = scBO.checkOtherRightByHouseID(houseID); //检查是否存在抵押
            if(list != null && list.size() > 0){
                map.put("result", "fail");
				map.put("msg", "该房屋存在抵押，请撤销抵押后继续网签！");
            }else{
                map.put("result", "success");
				map.put("msg", "没有抵押");
            }
            json = JSONArray.fromObject(map);
            return json;
        }catch (Exception e){
            e.printStackTrace();
            map.put("result", "error");
			map.put("msg", "抵押检查异常，请联系管理员");
            json = JSONArray.fromObject(map);
            return json;
        }
    }
    
    /**
	 * 功能描述：转到乙方签字列表
	 * @param request
	 * @return
	 */
    @RequestMapping(value = "/editBuyerSign")
    public String editBuyerSign(HttpServletRequest request,Page page,HttpSession session) {
    	try{
    		SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
    		String contractID = request.getParameter("contractID");
    		String earnestID = request.getParameter("earnestID");
    		SignContractBO scBO = new SignContractBO();
    		
			if(earnestID != null && !"".equals(earnestID)){ //若存在定金合同，将定金合同买方信息复制到合同买方信息中
				BuyerInfoVO buyerInfoVO = new BuyerInfoVO();
    			List<MetaFilter> params = new ArrayList<MetaFilter>();
    			params.add(new MetaFilter("contractID", "=", earnestID));
    			List<MetaOrder> orders = new ArrayList<MetaOrder>();
    			orders.add(new MetaOrder("serial", MetaOrder.ASC));
    			List<BuyerInfoVO> buyerList = scBO.search(BuyerInfoVO.class, params, orders, null);
    			if(buyerList != null && buyerList.size() > 0){
    				for(BuyerInfoVO bvo : buyerList){
    					buyerInfoVO.setContractID(Long.parseLong(contractID));
    					buyerInfoVO.setSerial(bvo.getSerial());
    					buyerInfoVO.setBuyerName(bvo.getBuyerName());
    					buyerInfoVO.setBuyerCardname(bvo.getBuyerCardname());
    					buyerInfoVO.setBuyerNationality(bvo.getBuyerNationality());
    					buyerInfoVO.setBuyerSex(bvo.getBuyerSex());
    					buyerInfoVO.setBuyerBirth(bvo.getBuyerBirth());
    					buyerInfoVO.setBuyerAddress(bvo.getBuyerAddress());
    					buyerInfoVO.setBuyerPostcode(bvo.getBuyerPostcode());
    					buyerInfoVO.setBuyerCardcode(bvo.getBuyerCardcode());
    					buyerInfoVO.setBuyerCall(bvo.getBuyerCall());
    					buyerInfoVO.setBuyerProxyType(bvo.getBuyerProxyType());
    					buyerInfoVO.setBuyerProxy(bvo.getBuyerProxy());
    					buyerInfoVO.setBuyerProxyAdr(bvo.getBuyerProxyAdr());
    					buyerInfoVO.setBuyerProxyCall(bvo.getBuyerProxyCall());
    					buyerInfoVO.setBuyerProvince(bvo.getBuyerProvince());
    					buyerInfoVO.setBuyerType(bvo.getBuyerType());
    					buyerInfoVO.setCrePerson(vo.getLoginName());
    					buyerInfoVO.setCreDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
    					buyerInfoVO.setCreTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
    					buyerInfoVO.setUpdPerson(vo.getLoginName());
    					buyerInfoVO.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
    					buyerInfoVO.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
    					scBO.add(buyerInfoVO);
    				}
    			}
    			ContractCancelPwdVO contractCancelPwdVO = new ContractCancelPwdVO();
    			List<MetaFilter> params1 = new ArrayList<MetaFilter>();
    			params1.add(new MetaFilter("contractID", "=", earnestID));
    			List<MetaOrder> orders1 = new ArrayList<MetaOrder>();
    			orders1.add(new MetaOrder("serial", MetaOrder.ASC));
    			List<ContractCancelPwdVO> pwdList = scBO.search(ContractCancelPwdVO.class, params1, orders1, null);
    			if(pwdList != null && pwdList.size() > 0){
    				for(ContractCancelPwdVO ccpVo : pwdList){
    					contractCancelPwdVO.setContractID(Long.parseLong(contractID));
    					contractCancelPwdVO.setSerial(ccpVo.getSerial());
    					contractCancelPwdVO.setPwd(ccpVo.getPwd());
    					scBO.add(contractCancelPwdVO);
    				}
    			}
    			
    		}
    		
			if(contractID != null && !"0".equals(contractID)){ //存在合同号，查询乙方信息列表
    			
    			List<MetaFilter> params = new ArrayList<MetaFilter>();
    			params.add(new MetaFilter("contractID", "=", contractID));
    			List<MetaOrder> orders = new ArrayList<MetaOrder>();
    			orders.add(new MetaOrder("serial", MetaOrder.ASC));
    			List<BuyerInfoVO> buyerList = scBO.search(BuyerInfoVO.class, params, orders, page);
    			if(buyerList != null && buyerList.size() > 0){
    				List<String> linkparam = new ArrayList<String>();
    				linkparam.add("contractID");
    				linkparam.add("serial");
    				
					/** 设置table列名 */
    				TableProperty tableProperty = new TableProperty();
    				tableProperty.setEnableSort(false);
    				tableProperty.setRowIndexStauts(false);
					tableProperty.addColumn("顺序", "serial");
					//    				tableProperty.addColumn("受让方(乙方)", "buyername", "doModify", linkparam, "buyername", null);
					tableProperty.addColumn("受让方(乙方)", "buyername");
					//    				tableProperty.addColumn("国籍", "buyer_nationality_dict_name");
					//    				tableProperty.addColumn("所在省市", "buyer_province_dict_name");
					tableProperty.addColumn("个人/公司", "buyer_type_dict_name");
					//    				tableProperty.addColumn("性别", "buyer_sex_dict_name");
					//    				tableProperty.addColumn("出生年月日", "buyerbirth", "Date", "yyyy-MM-dd", null);
					//    				tableProperty.addColumn("住所(址)", "buyeraddress");
					tableProperty.addColumn("邮编", "buyerpostcode");
					tableProperty.addColumn("证件名称", "buyer_cardname_dict_name");
					tableProperty.addColumn("证件号码", "buyercardcode");
					tableProperty.addColumn("联系电话", "buyercall");
					tableProperty.addColumn("委托/法定代理人", "buyerproxy");
					tableProperty.addColumn("住所(址)", "buyerproxyadr");
					tableProperty.addColumn("联系电话", "buyerproxycall");
					tableProperty.addColumn("是否签字", "signFlagStr");
					//    				tableProperty.addColumn("签字短信", "签字短信", "doSendSms", linkparam, "签字短信", null);
    				String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, buyerList, page, contractID);
    				
    				request.setAttribute("htmlView", htmlView);
    			}
    		}
    		
    		return "/fhhouse/salecontract/signcontract/BuyerSignInfo";
    	}catch (Exception e){
    		e.printStackTrace();
    		return ERROR_System;
    	}
    }
    
    /**
	 * 功能描述：发送短信
	 * @param request
	 * @return
	 */
    @RequestMapping(value = "/doSendSms")
    @ResponseBody
    public JSONArray doSendSms(HttpServletRequest request,HttpSession session) {
        JSONArray json = new JSONArray();
        Map<String , Object> map = new HashMap<String , Object>();
        ContractQueryBO cqBo = new ContractQueryBO();
        ContractPdfBO cpBo = new ContractPdfBO();
        SignContractBO sBo = new SignContractBO();
        RealestateFinder finder = new RealestateFinder();
        map.put("result", "success");
		map.put("msg", "短信发送成功！");
        try{
            String contractID = request.getParameter("contractID");
            String serialStr = request.getParameter("serial");
            ContractSignPdfVO cpvo = (ContractSignPdfVO) cpBo.find(ContractSignPdfVO.class, Long.parseLong(contractID));
			if(cpvo == null){
				map.put("result", "fail");
				map.put("msg", "请先点击 生成pdf！");
				return JSONArray.fromObject(map);
			}
            
            int serial = Integer.parseInt(serialStr);
            List<BuyerInfoVO> buyerList = cqBo.searchBuyerInfo(contractID);
            if(buyerList != null && buyerList.size() > 0 ){
            	HashMap<Integer, Integer> map2  = new HashMap<Integer, Integer>();
            	HashMap<Integer, BuyerInfoVO> buyerMap  = new HashMap<Integer, BuyerInfoVO>();
            	for (BuyerInfoVO buyerVO : buyerList) {
					int serialNum = buyerVO.getSerial();
					map2.put(serialNum, buyerVO.getSignFlag());
					buyerMap.put(serialNum, buyerVO);
				}
            	
            	for (int i = 1; i <= serial; i++) {
            		if(i < serial){
						if(map2.get(i) == 0){//未签
            				map.put("result", "fail");
							map.put("msg", "第" + i + "个顺序号还没有签字,请按照顺序号签字！");
            				return JSONArray.fromObject(map);
            			}
            		}else if (i == serial) {
            			if(map2.get(i) == 0){
            				int random = (int)((Math.random()*9+1)*100000);
							//发送签字短信
            				BuyerInfoVO buyer = (BuyerInfoVO)buyerMap.get(i);
            				if(buyer.getVerifyCode() != null && !"".equals(buyer.getVerifyCode())){
								//不发送短信
								map.put("result", "fail");
								map.put("msg", "已经发送过短信,请勿再次发送！");
                				return JSONArray.fromObject(map);
            				}
            				DocMessageVO message = new DocMessageVO();
            				message.setTransactionID(Long.parseLong(contractID));
    						message.setFlag(11);
    						message.setMobile(buyer.getBuyerCall());
    						message.setName(buyer.getBuyerName());
    						message.setContent(Constant.SmsURL+"?contractID="+contractID+"&cardNO="+buyer.getBuyerCardcode()+"&code="+random);
    						message.setWriteTime(DateUtil.getSysDate());
							message.setStatus(1);//1、有效
							long count = finder.addSignSms(message);
            				if(count > 0){
								//更新买方人表。
								//            					buyer.setVerifyCode(random+"");
//            					sBo.update(buyer);
            					List<MetaField> whereFields = new ArrayList<MetaField>();
            					whereFields.add(new MetaField("contractID", contractID));
            					whereFields.add(new MetaField("serial", serial+""));
            					
								List<MetaField> fields = new ArrayList<MetaField>();
								fields.add(new MetaField("verifyCode", random+""));
								sBo.update(BuyerInfoVO.class, whereFields , fields );
            				}
                		}else{
							//不发送短信
							map.put("result", "fail");
							map.put("msg", "已经签字！");
            				return JSONArray.fromObject(map);
                		}
					}
				}
            }
            json = JSONArray.fromObject(map);
            return json;
        }catch (Exception e){
            e.printStackTrace();
            map.put("result", "error");
			map.put("msg", "发短信异常！");
            json = JSONArray.fromObject(map);
            return json;
        }
    }
    
    /*
	 * 功能描述：检查是否签过字
	 */
	@RequestMapping("/checkContractPdfSign")
	@ResponseBody
	public JSONArray checkContractPdfSign(HttpServletRequest request,HttpSession session,HttpServletResponse response) {
		Map<String , Object> map = new HashMap<String , Object>();
		map.put("result", "1");
		map.put("msg", "");
		String contractID = request.getParameter("contractID");
		try{
			ContractQueryBO cqBo = new ContractQueryBO();
			ContractPdfBO cpBo = new ContractPdfBO();
			ContractSignPdfVO cpvo = (ContractSignPdfVO) cpBo.find(ContractSignPdfVO.class, Long.parseLong(contractID));
			if(cpvo != null){
				//检查是否买方已经签字
				boolean haveSign = false;
				List<BuyerInfoVO> buyerList = cqBo.searchBuyerInfo(String.valueOf(contractID));
				if(buyerList != null && buyerList.size() > 0){
					for (BuyerInfoVO buyerInfoVO : buyerList) {
						if(buyerInfoVO.getSignFlag() == 1){//已签字
							haveSign = true;
						}
					}
				}
				if(haveSign){
					map.put("result", "2");//有合同并签字的
				}else{
					map.put("result", "3");//有合同未签字的
				}
			}
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("合同[" + contractID + "]检查是否签过字异常！    " + DateUtil.getSysDate());
			map.put("result", "-1");
			map.put("msg", "检查异常！");
			return JSONArray.fromObject(map);
		}
		return JSONArray.fromObject(map);
	}
	
	
	/*
	 * 功能描述：删除电子合同
	 */
	@RequestMapping("/deleteSignContract")
	@ResponseBody
	public JSONArray deleteSignContract(HttpServletRequest request,HttpSession session,HttpServletResponse response) {
		Map<String , Object> map = new HashMap<String , Object>();
		map.put("result", "1");
		map.put("msg", "");
		String contractID = request.getParameter("contractID");
		try{
			String flag = request.getParameter("flag");//1、清空签字信息，0，不做操作
			ContractPdfBO cpBo = new ContractPdfBO();
			ContractSignPdfVO cpvo = (ContractSignPdfVO) cpBo.find(ContractSignPdfVO.class, Long.parseLong(contractID));
			if(cpvo != null){
				//删除电子合同
				List<MetaFilter> whereFields = new ArrayList<MetaFilter>();
				whereFields.add(new MetaFilter("contractID", "=", contractID));
				cpBo.delete(ContractSignPdfVO.class, whereFields);
				
				//清空签字信息
				if(flag != null && !"".equals(flag) && "1".equals(flag)){
					cpBo.updateBuyerInfo(Long.parseLong(contractID));
				}
			}
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("删除电子合同[" + contractID + "]出现异常！    " + DateUtil.getSysDate());
			map.put("result", "-1");
			map.put("msg", "删除电子合同出现异常！");
			return JSONArray.fromObject(map);
		}
		return JSONArray.fromObject(map);
	}

}
