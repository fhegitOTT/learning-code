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
	 * ???????????????????????????
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
	 * ?????????????????????????????????
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
		String buildingID = request.getParameter("buildingID"); //??????
        try{
            List<MetaFilter> params1 = new ArrayList<MetaFilter>();
            params1.add(new MetaFilter("building_ID", "=", buildingID));
            params1.add(new MetaFilter("signer_ID", "=", uservo.getUserId()));
            List<ProPreBldSignerVO> list1 = scBO.search(ProPreBldSignerVO.class, params1);
            if(list1 != null && list1.size() > 0){
				BuildingVO bvo = (BuildingVO) scBO.find(BuildingVO.class, Long.parseLong(buildingID)); //// ??????ID????????????
                if(bvo != null){
                    List<CHFlatVO> list2 = scBO.queryHouseList(buildingID);
                    if(list2 != null && list2.size() > 0){
                        map.put("result", "success");
                        map.put("message", "");
                    }else{
                        map.put("result", "fail");
						map.put("message", "???????????????????????????");
                    }
                }else{
                    map.put("result", "fail");
					map.put("message", "???????????????????????????????????????????????????");
                }
            }else{
                map.put("result", "fail");
				map.put("message", "???????????????????????????????????????????????????????????????");
            }
            json = JSONArray.fromObject(map);
            return json;
        }catch (Exception e){
            e.printStackTrace();
            map.put("result", "fail");
			map.put("message", "?????????????????????");
            json = JSONArray.fromObject(map);
            return json;
        }
    }

    /**
	 * ?????????????????????????????????
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
			String projectID = request.getParameter("projectID"); //??????
			String startID = request.getParameter("startID"); //?????????
			String buildingID = request.getParameter("buildingID"); //??????
			List<HouseVO> list = scBO.queryUserHouseListFHE(uservo.getUserId(), startID, buildingID); //????????????????????????????????????????????????
			List<HouseVO> list1 = rBo.findFloor(buildingID); //???????????????????????????
			request.setAttribute("list1", list1); //?????????????????????
			request.setAttribute("list", list); //?????????????????????
			request.setAttribute("projectID", projectID); //??????
			request.setAttribute("startID", startID); //?????????
			return "/fhe/salecontract/signcontract/SignContractQueryList";
        }catch (Exception e){
            e.printStackTrace();
            return ERROR_System;
        }
    }


    /**
	 * ???????????????????????????
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

			/* ???????????? */
            CHFlatVO chFlatVO = rBo.findhouseInfo(houseId);
            request.setAttribute("chFlatVO", chFlatVO);
			/* ???????????? */
            List<CHFlatVO> arList = rBo.findRealInfo(houseId);
            request.setAttribute("arList", arList);
			/* ??????????????? */
            List<HouseVO> permitList = rBo.queryPermitInfo(houseId);
			request.setAttribute("permitList", permitList);
			String ctype = "-1";//????????????????????????  -1??????????????????   1?????????  2?????????
			if(permitList != null && permitList.size() > 0){
                ctype = "1";
            }
			//????????????????????????1034????????????????????????
            if(rBo.checkHouseHasFileReg(houseId)){
                ctype = "2";
            }else{
				//String typeid = rBo.findTypebid(houseId);//????????????????????????
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

			/* ??????????????????????????????ID */
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
	 * ?????????????????????????????????
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
			int startResult = sBo.checkHouseExist(startId, houseId); //????????????????????????????????????
            if(startResult > 0){
				int signerResult = sBo.checkSignerHouseRelate(signerId, houseId); //????????????????????????????????????????????????
                if(signerResult > 0){
                    CHFlatVO chFlatVO = (CHFlatVO) rBo.find(CHFlatVO.class, Long.parseLong(houseId));
                    map.put("result", "success");
                    map.put("message", "");

                    if(chFlatVO != null){
                        HouseVO hvo = (HouseVO) sBo.find(HouseVO.class, Long.parseLong(houseId));
                    }else{
                        map.put("result", "fail");
						map.put("message", "??????????????????????????????????????????????????????");
                    }

                }else{
                    map.put("result", "fail");
					map.put("message", "???????????????????????????????????????????????????????????????");
                }
            }else{
                map.put("result", "fail");
				map.put("message", "??????????????????????????????????????????????????????");
            }

            json = JSONArray.fromObject(map);
            return json;
        }catch (Exception e){
            e.printStackTrace();
            map.put("result", "fail");
			map.put("message", "????????????????????????");
            json = JSONArray.fromObject(map);
            return json;
        }
    }


    /**
	 * ?????????????????????????????????
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
		params.add(new MetaFilter("contractID", "=", contractID)); //???????????????
		List<MetaOrder> orders = new ArrayList<MetaOrder>();
		orders.add(new MetaOrder("serial", MetaOrder.ASC)); //??????????????????serial??????????????????
		List<BuyerInfoVOFHE> buyerList = scBO.search(BuyerInfoVOFHE.class, params, orders, null); //???????????????contractID=contractID?????????????????????serial???????????????????????????
		request.getSession().setAttribute("buyerList", buyerList);

		return "/fhe/salecontract/signcontract/ContractCreate";
	}


	/**
	 * ?????????????????????????????????
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

		//??????????????????
		EnterpriseQualifyVO eqvo = (EnterpriseQualifyVO) scBO.find(EnterpriseQualifyVO.class, vo.getOrgID());
		//????????????
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

		//???????????????
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
		//????????????
		CHLocationVO locationVo = rBo.findLocationInfo(houseId);
		if(locationVo != null){
			cdVo.setRoad(locationVo.getRoadName());
			cdVo.setLaneName(locationVo.getLaneName());
			cdVo.setSubLane(locationVo.getSubLane());
			cdVo.setBuildingNumber(locationVo.getStreetNumber());
			cdVo.setAlley(locationVo.getLaneName() + locationVo.getSubLane());
		}

		cdVo.setLandUseCode(Long.parseLong(chFlatVO.getHouse_Use())); //??????????????????
		DictionaryBO dicBO = new DictionaryBO();
		int landType = dicBO.getHouseLandType(chFlatVO.getHouse_Use());
		cdVo.setLandType(landType);//????????????????????? 1:?????????2:?????????3:?????????4:??????
		cdVo.setSigner(String.valueOf(vo.getUserId())); //???????????????
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
		//????????????
		List<AttachVO> attachList = new ArrayList<AttachVO>();
		if(type != null && "2".equals(type)){
			cdVo.setContractversion(FHConstant.NEW_SC_CONTRACTVERSION);
			//????????????
			ContractDetailCsVO scVo = new ContractDetailCsVO();
			scVo.setContractID(Long.parseLong(new SystemBO().getSequence("CONTRACTID")));
			scVo.setCrePerson(person);
			scVo.setCreDate(dates);
			scVo.setCreTime(times);
			scVo.setUpdPerson(person);
			scVo.setUpdDate(dates);
			scVo.setUpdTime(times);
			//????????????
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
	 * ?????????????????????????????????
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
			if(contractID != null && !"0".equals(contractID)){ //??????????????????????????????????????????
				Map<String , Object> result = new HashMap<String , Object>();
				List<MetaFilter> params = new ArrayList<MetaFilter>();
				params.add(new MetaFilter("contractID", "=", contractID)); //???????????????
				List<MetaOrder> orders = new ArrayList<MetaOrder>();
				orders.add(new MetaOrder("serial", MetaOrder.ASC)); //??????????????????serial??????????????????
				List<BuyerInfoVOFHE> buyerList = scBO.search(BuyerInfoVOFHE.class, params, orders, null); //???????????????contractID=contractID?????????????????????serial???????????????????????????
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
	 * ???????????????????????????????????????????????????
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/editBuyer1FHE")
	public String editBuyer1FHE(HttpServletRequest request,Page page,HttpSession session) {
		try{
			String contractID = request.getParameter("contractID");
			SignContractBO scBO = new SignContractBO();

			if(contractID != null && !"0".equals(contractID)){ //??????????????????????????????????????????
				List<MetaFilter> params = new ArrayList<MetaFilter>();
				params.add(new MetaFilter("contractID", "=", contractID)); //???????????????
				List<MetaOrder> orders = new ArrayList<MetaOrder>();
				orders.add(new MetaOrder("serial", MetaOrder.ASC)); //??????????????????serial??????????????????
				List<BuyerInfoVOFHE> buyerList = scBO.search(BuyerInfoVOFHE.class, params, orders, page); //???????????????contractID=contractID?????????????????????serial???????????????????????????
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
	 * ???????????????????????????????????????????????????
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/operaBuyerInfoFHE")
	public String operaBuyerInfo(HttpServletRequest request) {
		try{
			String contractID = request.getParameter("contractID");
			String serial = request.getParameter("serial");
			SignContractBO scBO = new SignContractBO();
			if(contractID != null && !"".equals(contractID) && serial != null && !"".equals(serial)){ //??????????????????????????????????????????????????????
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
	 * ?????????????????????????????????
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/editSellerFHE")
	public String editSellerFHE(HttpServletRequest request,Page page,HttpSession session) {
		try{
			SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
			String contractID = request.getParameter("contractID");
			String earnestID = request.getParameter("earnestID"); //??????????????????
			SignContractBO scBO = new SignContractBO();

			if(earnestID != null && !"".equals(earnestID)){ //?????????????????????????????????????????????????????????????????????????????????
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

			if(contractID != null && !"0".equals(contractID)){ //??????????????????????????????????????????

				List<MetaFilter> params = new ArrayList<MetaFilter>();
				params.add(new MetaFilter("contractID", "=", contractID));
				List<MetaOrder> orders = new ArrayList<MetaOrder>();
				orders.add(new MetaOrder("serial", MetaOrder.ASC));
				List<SellerInfoVO> sellerList = scBO.search(SellerInfoVO.class, params, orders, page);
				if(sellerList != null && sellerList.size() > 0){
					List<String> linkparam = new ArrayList<String>();
					linkparam.add("contractid");
					linkparam.add("serial");
					/** ??????table?????? */
					TableProperty tableProperty = new TableProperty();
					tableProperty.setEnableSort(false);
					tableProperty.setRowIndexStauts(false);
					tableProperty.addColumn("??????", "serial");
					tableProperty.addColumn("??????(??????)", "buyername", "doModify", linkparam, "buyername", null);
					tableProperty.addColumn("??????", "buyer_nationality_dict_name");
					tableProperty.addColumn("????????????", "buyer_province_dict_name");
					tableProperty.addColumn("??????/??????", "buyer_type_dict_name");
					tableProperty.addColumn("??????", "buyer_sex_dict_name");
					tableProperty.addColumn("???????????????", "buyerbirth", "Date", "yyyy-MM-dd", null);
					tableProperty.addColumn("??????(???)", "buyeraddress");
					tableProperty.addColumn("??????", "buyerpostcode");
					tableProperty.addColumn("????????????", "buyer_cardname_dict_name");
					tableProperty.addColumn("??????", "buyercardcode");
					tableProperty.addColumn("????????????", "buyercall");
					//tableProperty.addColumn("??????/???????????????", "buyerproxy");
					//tableProperty.addColumn("??????(???)", "buyerproxyadr");
					//tableProperty.addColumn("????????????", "buyerproxycall");
					linkparam.add("buyername");
					tableProperty.addColumn("??????", "??????", "doDelete", linkparam, "??????", null);
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
	 * ???????????????????????????//?????????????????????
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/operaBuyerInfoFHEAdd")
	public String operaBuyerInfoFHEAdd(HttpServletRequest request) {
		try{
			String contractID = request.getParameter("contractID");
			String serial = request.getParameter("serial");
			SignContractBO scBO = new SignContractBO();
			if(contractID != null && !"".equals(contractID) && serial != null && !"".equals(serial)){ //??????????????????????????????????????????????????????
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
	 * ?????????????????????????????????
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
				//??????????????????  ContractCancelPwdVO ccpVo = new ContractCancelPwdVO();

				//????????????
                if("-99".equals(biVo.getBuyerSex())){
                    biVo.setBuyerSex(null);
                }

				//if(serial != 0){ //?????????????????????????????????????????????????????????
				List<MetaFilter> params = new ArrayList<MetaFilter>();
				params.add(new MetaFilter("contractID", "=", contractID));
				List<MetaOrder> orders = new ArrayList<MetaOrder>();
				orders.add(new MetaOrder("serial", MetaOrder.DESC));
				List<BuyerInfoVOFHE> buyerList = scBO.search(BuyerInfoVOFHE.class, params, orders, null); //???????????????????????????????????????????????????
				int serialnext = 1; //???????????????????????????1??????
				if(buyerList != null && buyerList.size() > 0){
					request.getSession().setAttribute("buyerList", buyerList);
					BuyerInfoVOFHE buyerInfo = buyerList.get(0); //???????????????????????????????????????
					serialnext = buyerInfo.getSerial() + 1; //?????????????????????????????????
				}

				biVo.setSerial(serialnext);

				res = scBO.saveBuyerInfoFHE(biVo, 1);
				
            }

            if(res == true){
                map.put("result", "success");
				map.put("msg", "?????????????????????");
            }else{
                map.put("result", "fail");
				map.put("msg", "?????????????????????");
            }
            json = JSONArray.fromObject(map);
			return map;
        }catch (Exception e){
            e.printStackTrace();
            map.put("result", "fail");
			map.put("msg", "???????????????????????????");
            json = JSONArray.fromObject(map);
			return map;
        }
    }

    /**
	 * ???????????????????????????
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
					map.put("msg", "?????????????????????");
				}else{
					map.put("result", "fail");
					map.put("msg", "?????????????????????");
				}
			}else{
				map.put("result", "fail");
				map.put("msg", "??????????????????????????????");
			}
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "fail");
			map.put("message", "?????????????????????");
			json = JSONArray.fromObject(map);
			return json;
		}
	}


	/**
	 * ???????????????????????????
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
						map.put("msg1", "?????????????????????");
					}else{
						map.put("result", "fail");
						map.put("msg", "?????????????????????");
					}
				}
				//map.get()
				map.put("length", "" + chk_value.length);
				/*
				 * if(res == true){ map.put("result", "success"); map.put("msg", "?????????????????????"); }else{ map.put("result", "fail"); map.put("msg", "?????????????????????"); }
				 */
			}else{
				map.put("result", "fail");
				map.put("msg", "??????????????????????????????");
			}
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "fail");
			map.put("message", "?????????????????????");
			json = JSONArray.fromObject(map);
			return json;
		}
	}

	/**
	 * ?????????????????????????????????
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
			/* ???????????? */
			ContractDealFHEVO cdVO = new ContractDealFHEVO();
			cdVO.setContractID(contractID);
			cdVO = (ContractDealFHEVO) scBO.find(cdVO);
			request.setAttribute("cdVO", cdVO);

			/* ???????????? */
			List<MetaFilter> params = new ArrayList<MetaFilter>();
			params.add(new MetaFilter("contractID", "=", contractID));
			List<MetaOrder> orders = new ArrayList<MetaOrder>();
			orders.add(new MetaOrder("serial", MetaOrder.ASC));
			List<BuyerInfoVO> buyerList = scBO.search(BuyerInfoVO.class, params, orders, null);
			request.getSession().setAttribute("buyerList", buyerList);

			/* ???????????? */
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
	 * ???????????????????????????????????????FHE
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
	 * ?????????????????????(??????)????????????
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
			//List<Object> districtList = this.getUserDistricts(vo.getRegionId());//??????????????????????????????
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
	 * ???????????????????????????????????????FHE
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
	 * ?????????????????????????????????FHE
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
			//List<Object> districtList = this.getUserDistricts(vo.getRegionId());//??????????????????????????????
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
	 * ?????????????????????(??????)????????????
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
			//List<Object> districtList = this.getUserDistricts(vo.getRegionId());//??????????????????????????????
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
			/** ??????table?????? */
            TableProperty tableProperty = new TableProperty();
            tableProperty.setEnableSort(false);
            tableProperty.setRowIndexStauts(true);
			tableProperty.addColumn("?????????", "contractID", "doContractEdit", linkparam);
			tableProperty.addColumn("????????????", "type_dict_name");
			tableProperty.addColumn("????????????", "location");
			tableProperty.addColumn("??????", "buyerName");
            if("waiting".equals(cmd)){
				tableProperty.addColumn("?????????", "crepersonname");
            }
			tableProperty.addColumn("????????????", "signDate1", "Date", "yyyy-MM-dd HH:mm:ss", null);
			tableProperty.addColumn("????????????", "waitday");

            if("edit".equals(cmd)){
				tableProperty.addColumn("????????????", "????????????", "xgInfo", linkparam1, "????????????", null);
				tableProperty.addColumn("??????", "??????", "delContract", linkparam, "??????", null);
            }
			tableProperty.addColumn("????????????", "????????????", "viewPrint", linkparam, "????????????", null);
			tableProperty.addColumn("??????ID", "contractID", true);

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
	 * ???????????????????????????FHE
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
				map.put("message", "?????????????????????");
			}else{
				map.put("result", "fail");
				map.put("message", "????????????????????????");
			}
			json = JSONArray.fromObject(map);
			return json;
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", "fail");
			map.put("message", "??????????????????");
			json = JSONArray.fromObject(map);
			return json;
		}
	}

	/**
	 * ?????????????????????????????????
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
				map.put("message", "?????????????????????");
            }else{
                map.put("result", "fail");
				map.put("message", "????????????????????????");
            }
            json = JSONArray.fromObject(map);
            return json;
        }catch (Exception e){
            e.printStackTrace();
            map.put("result", "fail");
			map.put("message", "??????????????????");
            json = JSONArray.fromObject(map);
            return json;
        }
    }

    /**
	 * ???????????????????????????
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
	 * ?????????????????????????????????
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
			/** ??????table?????? */
            TableProperty tableProperty = new TableProperty();
            tableProperty.setEnableSort(false);
            tableProperty.setRowIndexStauts(true);
            tableProperty.addSelectControl("radio", "selContractID", linkparam1, "");
			tableProperty.addColumn("?????????", "contractID");
			tableProperty.addColumn("????????????", "type_dict_name");
			tableProperty.addColumn("????????????", "location");
			tableProperty.addColumn("??????", "buyerName");
			tableProperty.addColumn("??????", "area");
			tableProperty.addColumn("??????", "money");
			tableProperty.addColumn("????????????", "confirmDate1");
			tableProperty.addColumn("?????????????????????", "waitday");
			tableProperty.addColumn("??????", "status_dict_name");
			//tableProperty.addColumn("????????????", "????????????", "viewPrint", linkparam, "????????????", null);
			tableProperty.addColumn("??????ID", "contractID", true);

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
	 * ???????????????(???????????????)????????????
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
	 * ?????????????????????????????????
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
			/* ???????????? */
            List<MetaFilter> params = new ArrayList<MetaFilter>();
            params.add(new MetaFilter("contractID", "=", contractID));
            List<SellerInfoVO> list = scBO.search(SellerInfoVO.class, params);
            if(list != null && list.size() > 0){
                SellerInfoVO svo = (SellerInfoVO) list.get(0);
                request.setAttribute("svo", svo);
            }
			/* ???????????? */
            List<MetaFilter> params1 = new ArrayList<MetaFilter>();
            params1.add(new MetaFilter("contractID", "=", contractID));
            List<MetaOrder> orders1 = new ArrayList<MetaOrder>();
            orders1.add(new MetaOrder("serial", MetaOrder.ASC));
            List<BuyerInfoVO> buyerList = scBO.search(BuyerInfoVO.class, params1, orders1, null);
            request.setAttribute("buyerList", buyerList);
			/* ?????????????????? */
            Attach4VO attach4vo = (Attach4VO) scBO.find(Attach4VO.class, Long.parseLong(contractID));
            request.setAttribute("attach4vo", attach4vo);
			/* ???????????? */
            List<Attach4RealVO> arList = scBO.queryAttach4RealInfo(contractID);
            request.setAttribute("arList", arList);
			/* ???????????? */
            List<Attach4OtherVO> aoList = scBO.queryAttach4OtherInfo(contractID);
            request.setAttribute("aoList", aoList);
			/* ???????????? */
            List<Attach4LimitVO> alList = scBO.queryAttach4LimitInfo(contractID);
            request.setAttribute("alList", alList);
			/* ???????????? */
            List<Attach4HireVO> ahList = scBO.queryAttach4HireInfo(contractID);
            request.setAttribute("ahList", ahList);
			/* ??????????????? */
            /*
             * List<HouseVO> permitList = scBO.queryPermitInfo(String.valueOf(cdvo.getHouseID())); request.setAttribute("permitList", permitList);
             */
			/* ???????????? */
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
			/* ???????????? */
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
	 * ?????????????????????????????????
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
				throw new Exception("?????????????????????");
            }
            return "redirect:/outer/signcontract/queryWaitingConfirmContract";
        }catch (Exception e){
            e.printStackTrace();
            return ERROR_System;
        }
    }



    /**
	 * ?????????????????????????????????
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
			//??????????????????????????????????????????????????????????????????????????????????????????????????????????????????
			String cmd = request.getParameter("cmdFHE");
            long i = -1;
            long contractID = Long.parseLong(contractid);
            List<MetaFilter> buyerparams = new ArrayList<MetaFilter>();
            buyerparams.add(new MetaFilter("contractID", "=", contractID));
			List<BuyerInfoVOFHE> buyerlist = scBO.search(BuyerInfoVOFHE.class, buyerparams);

			//???????????????????????????
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
				//??????????????????
                scVo.setContractID(contractID);
                scVo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
                scVo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
                scVo.setUpdPerson(vo.getLoginName());

                //ContractDealVO cdVo=(ContractDealVO)scmBo.find(ContractDealVO.class, contractID);
				//??????????????????
                cdVo.setContractID(contractID);
				cdVo.setSellerName(request.getParameter("sellerName")); //????????????
                /*
				 * if(request.getParameter("landUse") != null && !"".equals(request.getParameter("landUse"))){ cdVo.setLandUseCode(Long.parseLong(request.getParameter("landUse")) + 1000); //?????????????????? int
				 * landType = dicBO.getHouseLandType(String.valueOf(cdVo.getLandUseCode()));//FHConstant.getLandType(Integer.parseInt(request.getParameter("landUse"))); cdVo.setLandType(landType);
				 * //????????????????????? 0:?????????1:?????????2:?????????3:?????? }
				 */

                List<MetaFilter> params = new ArrayList<MetaFilter>();
                params.add(new MetaFilter("contractID", "=", contractID));
                List<MetaOrder> orders = new ArrayList<MetaOrder>();
                orders.add(new MetaOrder("serial", MetaOrder.ASC));
				List list = scBO.search(BuyerInfoVOFHE.class, params, orders, null);
                if(list.size() == 1){
					BuyerInfoVOFHE bvo = (BuyerInfoVOFHE) list.get(0);
					cdVo.setBuyerName(bvo.getBuyerName()); //????????????
                }
                if(list.size() > 1){
					BuyerInfoVOFHE bvo = (BuyerInfoVOFHE) list.get(0);
					cdVo.setBuyerName(bvo.getBuyerName() + "..."); //????????????
                }



                ContractDealVO earnestVO = null;

                if("editSubmit".equals(cmd)){
					cdVo.setStatus(FHConstant.CONTRACT_STATUS_WAIT4SIGN); //?????????????????????????????????
				}
                if("waitingSubmit".equals(cmd)){
					cdVo.setStatus(FHConstant.CONTRACT_STATUS_SIGNED); //??????
					hvo = (HouseVO) scBO.find(HouseVO.class, cdVo.getHouseID());
					hvo.setStatus(FHConstant.HOUSE_STATUS_SIGNED); //????????????
					// hvo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
					// hvo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
                    hvo.setUpdPerson(vo.getLoginName());
                }

				//????????????
                siVo.setContractID(contractID);
                siVo.setSerial(1);
                siVo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
                siVo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
                siVo.setUpdPerson(vo.getLoginName());

                List<AttachVO> attachList = new ArrayList<AttachVO>();
				//????????????
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

			//			/* ???????????????????????? */
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
				map.put("msg", "???????????????????????????");
            }else if(i == -9999999){
                map.put("result", "fail");
				map.put("msg", "?????????????????????????????????");
				//			}else if(i == -111){
				//				map.put("result", "fail");
				//				map.put("msg", "????????????????????????" + String.valueOf(hbaVo.getNHouseAverage()) + " (???/???), ????????????" + String.valueOf(hbaVo.getNBandAverage()) + "(???/???)???\n???????????????????????????????????????????????????");
				//			}else if(i == -222){
				//				map.put("result", "fail");
				//				map.put("msg", "?????????????????????????????????????????????????????????????????????????????????");
            }else{
                map.put("result", "fail");
				map.put("msg", "???????????????????????????");
            }

            json = JSONArray.fromObject(map);
            return json;
        }catch (Exception e){
            e.printStackTrace();
            map.put("result", "fail");
			map.put("msg", "???????????????????????????");
            json = JSONArray.fromObject(map);
            return json;
        }
    }
    


    
    
    
    	/**
	 * ?????????????????????????????????
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

			/** 2018-12-05 added by gcf ??????????????????????????? **/
            String houseID = request.getParameter("houseID");
            List<HouseVO> houseList = sBo.checkOtherRightByHouseID(houseID);
            if(houseList != null && houseList.size() > 0){
                map.put("result", "fail");
				map.put("msg", "?????????????????????????????????????????????????????????");
                json = JSONArray.fromObject(map);
                return json;
            }
			/** 2018-12-05 added by gcf ??????????????????????????? **/


            List<MetaFilter> params = new ArrayList<MetaFilter>();
            params.add(new MetaFilter("contractID", "=", contractID));
            List<MetaOrder> orders = new ArrayList<MetaOrder>();
            orders.add(new MetaOrder("serial", MetaOrder.ASC));
            List<BuyerInfoVO> buyerList = sBo.search(BuyerInfoVO.class, params, orders, null);
            List<ContractCancelPwdVO> ccPwdList = sBo.search(ContractCancelPwdVO.class, params, orders, null);
            List<SellerInfoVO> sellerList = sBo.search(SellerInfoVO.class, params, orders, null);

            if(buyerList != null && !buyerList.isEmpty()){
            	//if(1!=1){
				/*** 20181120 added by gcf ?????????????????????????????????????????????????????????????????????????????????-start ****/
                ZBReader reader = new ZBReader();
                for(BuyerInfoVO bvo : buyerList){
                    HashMap resultMap = reader.getToken(Constant.username, Constant.password);
                    if(resultMap != null){
						if("1".equals(resultMap.get("result").toString())){//?????????
							//??????token
                            String token = resultMap.get("token").toString();
							//???????????????????????????????????????????????????
							resultMap = reader.getZBHouseInfo(bvo.getBuyerName(), bvo.getBuyerCardcode(), token);
                            if(resultMap != null){
                                if("1".equals(resultMap.get("result").toString())){
                                    String data = resultMap.get("data").toString();
                                    String arr[] = data.split(",");
                                    if(arr != null && arr.length > 0){
                                        String distName = sBo.getDistNameForZB(arr[0].substring(0, 2).toUpperCase());
                                        map.put("result", "fail");
										map.put("msg", "????????????????????????" + bvo.getBuyerCardcode() + "????????????????????????" + arr[0] + "???????????????????????????????????????" + distName + "?????????????????????????????????????????????????????????");
                                        return JSONArray.fromObject(map);
                                    }
                                }
                            }else{
                                map.put("result", "fail");
								map.put("msg", "??????????????????????????????????????????");
                                return JSONArray.fromObject(map);
                            }
                        }
                    }else{
                        map.put("result", "fail");
						map.put("msg", "?????????????????????????????????");
                        return JSONArray.fromObject(map);
                    }
                }
            	//}
            }
            String msg=null;
            if(buyerList != null && buyerList.size() > 0 && ccPwdList != null && buyerList.size() == ccPwdList.size()){
            }else{
				msg = "????????????????????????????????????????????????";
            }

            RealestateBO rBo = new RealestateBO();
            if(msg==null){
                CHFlatVO cfvo = (CHFlatVO) rBo.find(CHFlatVO.class, Long.parseLong(houseId));
                if(cfvo == null){
					msg = "????????????????????????????????????";
                }
            }
            if(msg==null){
                int startResult = sBo.checkHouseExist(startId, houseId);
                if(startResult <= 0){
					msg = "??????????????????????????????????????????????????????";
                }
            }
            if(msg==null){
                int signerResult = sBo.checkSignerHouseRelate(signerId, houseId);
                if(signerResult <= 0){
					msg = "???????????????????????????????????????????????????????????????";
                }
            }
            if(msg==null){
                int limitResult = rBo.checkHouseLimit(houseId);
                if(limitResult > 0){
					msg = "???????????????????????????????????????";
                }
            }
            if(msg==null){
                int disscentResult = rBo.checkHouseDisscent(houseId);
                if(disscentResult > 0){
					msg = "???????????????????????????????????????";
                }
            }
            if(msg==null){
                HouseVO hvo = (HouseVO) sBo.find(HouseVO.class, Long.parseLong(houseId));
                if(! (hvo.getStatus() == 4 || hvo.getStatus() == 8)){
					msg = "????????????????????????????????????";
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
					msg = "??????????????????????????????????????????????????????????????????????????????????????????!";
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
			//						msg = "?????????????????????";
//					}
//				}else{
			//					msg = "???????????????????????????";
//				}
//				if(maxPrice < unitPrice){
			//					msg = "???????????????????????????????????????????????????????????????";
//				}
//			}

			//???????????????????????? 
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
							msg = "????????????????????????" + String.valueOf(hbaVo.getNHouseAverage()) + " (???/???), ????????????" + String.valueOf(hbaVo.getNBandAverage()) + "(???/???)???\n?????????????????????????????????????????????";
                        }else{
							//						msg = "??????????????????????????????";
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
										msg = "????????????????????????" + String.valueOf(hbaVo.getNHouseAverage()) + " (???/???), ????????????" + String.valueOf(hbaVo.getNBandAverage()) + "(???/???)???\n?????????????????????????????????????????????";
										System.out.println("??????(" + contractID + ")?????????????????????????????????????????????unitPrice=" + unitPrice);
                                    }
                                }else{
									msg = "??????????????????";
                                }
                            }
                        }else{
							msg = "???????????????????????????";
                        }
                    }
                }
            }

			//????????????????????????
            if(msg==null){
                HouseVO houseVo = (HouseVO) sBo.find(HouseVO.class, Long.parseLong(houseId));
                HMFCityVO hcvo = (HMFCityVO) sBo.find(HMFCityVO.class, houseVo.getDistrictID());
                if(hcvo != null && hcvo.getNstate() == 1){
                    List<HouseVO> housePayList = sBo.queryHousePayInfo(houseId);
                    if(housePayList == null || housePayList.size() <= 0){
						msg = "????????????????????????????????????????????????????????????????????????????????????????????????.";
                    }else{
                        for(HouseVO housePay : housePayList){
                            if("0".equals(housePay.getAttribute("audit_status").toString())){
								msg = "????????????????????????????????????????????????????????????(??????????????????),?????????????????????????????????.";
                            }if("1".equals(housePay.getAttribute("repay_flag").toString())){
								msg = "????????????????????????????????????????????????????????????(?????????????????????),?????????????????????????????????.";
                            }
                        }
                    }
                }
            }

			//??????????????????
            if(msg == null){
                String xgID = request.getParameter("xgID");
                boolean xgFlag = true;
                
				//2020.3.23 ph ??????????????????????????????????????????????????????
            	long xgManCheckState = sBo.findxgManCheckState(houseId);
				//??????????????????????????????
                List<MetaFilter> params2 = new ArrayList<MetaFilter>();
                params2.add(new MetaFilter("contractID", "=", contractID));
				//????????????????????????????????????
                if(xgManCheckState==1){
                	params2.add(new MetaFilter("buyerType", "=", 0));
                }
                List<MetaOrder> orders2 = new ArrayList<MetaOrder>();
                orders2.add(new MetaOrder("serial", MetaOrder.ASC));
                List<BuyerInfoVO> xgCBuyerList = sBo.search(BuyerInfoVO.class, params2, orders2, null);
				//????????????????????????????????????
                if(xgCBuyerList!=null  && xgCBuyerList.size()>0){
					//????????????????????????
	                for(BuyerInfoVO bvo : xgCBuyerList){
	                    if(!"0".equals(bvo.getBuyerType())){
	                        xgFlag = false;
	                        break;
	                    }
	                }
	
	                if(xgFlag){
						//????????????????????????
	                    long nState = sBo.findState(houseId);
						//???????????????????????????
						CHFlatVO flatvo = new RealestateBO().findCHFlat(Long.parseLong(houseId));
	                    boolean isXG = true;
	                    String houseType = flatvo.getAttribute("house_type") == null ? "" : flatvo.getAttribute("house_type").toString();
	                    if(!"".equals(houseType) && houseType != null){
	                        int ht = Integer.parseInt(houseType);
							//?????????????????????
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
								msg = "?????????????????????????????????????????????????????????";
	                        }else{
								//????????????????????????????????????????????????
	                            for(BuyerInfoVO bvo : xgCBuyerList){
	                                List<XgBuyerOwnerNameVO> xgBuyerlist = sBo.queryXgBuyerInfo(xgID, bvo.getBuyerName(), bvo.getBuyerCardcode());
	                                if(xgBuyerlist == null || xgBuyerlist.isEmpty()){
										msg = "???????????????????????????????????????????????????????????????????????????????????????????????????????????????";
	                                    break;
	                                }
	                            }
	
	                        }
	                    }else{
	                        if(xgID != null && !"".equals(xgID)){
								msg = "??????????????????????????????????????????????????????????????????????????????????????????????????????";
	                        }
	                    }
	
	                }else{
	                    if(xgID != null && !"".equals(xgID)){
							msg = "?????????????????????????????????????????????????????????????????????????????????";
	                    }
	                }
                }else{
                	if(xgID != null && !"".equals(xgID)){
						msg = "?????????????????????????????????????????????????????????????????????????????????";
                    }
                }
            }

			//??????????????????
            if(msg == null && "1".equals(type)){
                List<MetaFilter> paramsa = new ArrayList<MetaFilter>();
                paramsa.add(new MetaFilter("contractID", "=", contractID));
                List<PresellAttach2MoneyVO> palist = sBo.search(PresellAttach2MoneyVO.class, paramsa);
				//???????????????????????????
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
					//????????????????????????
                    double totalmoney = palist.get(0).getTotalMoney();
					//????????????????????????????????????????????????????????????
                    if(Math.abs(cTotalmoney - totalmoney) >= 0.01){
						msg = "??????????????????????????????????????????????????????????????????????????????????????????????????????";
                    }else{
                        double money = 0;
                        for(PresellAttach2MoneyVO pa : palist){
                            money += pa.getMoney();
                        }
                        if(Math.abs(money - totalmoney) >= 0.01){
							msg = "??????????????????????????????????????????????????????????????????????????????";
                        }
                    }
                }else{
					msg = "???????????????????????????????????????";
                }
            }

			//????????????
            if(msg == null && "1".equals(type)){
                String regulator = request.getParameter("f0725");
                String manageAcctName = request.getParameter("f0726");
                String manageAcct = request.getParameter("f0727");
                if(regulator != null && !"".equals(regulator) && (manageAcctName == null || "".equals(manageAcctName) || manageAcct == null || "".equals(manageAcct))){
					msg = "???????????????????????????????????????????????????";
                }
//				HouseVO hvo = (HouseVO) sBo.find(HouseVO.class, Long.parseLong(houseId));
//				if(hvo != null){
//					PresellDistrictidManageVO pdmVo = (PresellDistrictidManageVO) sBo.find(PresellDistrictidManageVO.class, hvo.getDistrictID());
//					PresellManageVO pmVo = (PresellManageVO) sBo.find(PresellManageVO.class, hvo.getBuilding_ID());
//					if(pdmVo != null && pdmVo.getState() == 1){
//						if(pmVo == null || (pmVo.getManage_Acct() == null || "".equals(pmVo.getManage_Acct()) || pmVo.getManage_Acct_Name() == null || "".equals(pmVo.getManage_Acct_Name()))){
				//							msg = "???????????????????????????????????????????????????";
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
			map.put("msg", "???????????????????????????????????????????????????");
        }
        json = JSONArray.fromObject(map);
        return json;
    }

    /**
	 * ?????????????????????????????????????????????
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
			List<CompCancelPwdVO> compList = scBO.search(CompCancelPwdVO.class, params); //????????????ID??????????????????????????????
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
	 * ?????????????????????????????????????????????
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
	 * ??????????????????????????????
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
	 * ??????????????????????????????
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
	 * ??????????????????????????????
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
				map.put("msg", "????????????" + pa.getContractID() + "???????????????????????????");
            }else{
                map.put("result","fail");
				map.put("msg", "???????????????????????????????????????");
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("result", "fail");
			map.put("msg", "????????????????????????????????????");
        }
        return JSONArray.fromObject(map);
    }

    /**
	 * ??????????????????????????????
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
				map.put("msg", "????????????" + contractID + "???????????????????????????");
            }else{
                map.put("result", "fail");
				map.put("msg", "??????????????????????????????????????????");
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("result", "fail");
			map.put("msg", "????????????????????????????????????");
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
				map.put("msg", "????????????????????????");
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
			map.put("msg", "????????????????????????????????????");
        }
        return JSONArray.fromObject(map);
    }

    /**
	 * ?????????????????????????????????
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
			List<HouseVO> list = scBO.checkOtherRightByHouseID(houseID); //????????????????????????
            if(list != null && list.size() > 0){
                map.put("result", "fail");
				map.put("msg", "?????????????????????????????????????????????????????????");
            }else{
                map.put("result", "success");
				map.put("msg", "????????????");
            }
            json = JSONArray.fromObject(map);
            return json;
        }catch (Exception e){
            e.printStackTrace();
            map.put("result", "error");
			map.put("msg", "???????????????????????????????????????");
            json = JSONArray.fromObject(map);
            return json;
        }
    }
    
    /**
	 * ???????????????????????????????????????
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
    		
			if(earnestID != null && !"".equals(earnestID)){ //?????????????????????????????????????????????????????????????????????????????????
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
    		
			if(contractID != null && !"0".equals(contractID)){ //??????????????????????????????????????????
    			
    			List<MetaFilter> params = new ArrayList<MetaFilter>();
    			params.add(new MetaFilter("contractID", "=", contractID));
    			List<MetaOrder> orders = new ArrayList<MetaOrder>();
    			orders.add(new MetaOrder("serial", MetaOrder.ASC));
    			List<BuyerInfoVO> buyerList = scBO.search(BuyerInfoVO.class, params, orders, page);
    			if(buyerList != null && buyerList.size() > 0){
    				List<String> linkparam = new ArrayList<String>();
    				linkparam.add("contractID");
    				linkparam.add("serial");
    				
					/** ??????table?????? */
    				TableProperty tableProperty = new TableProperty();
    				tableProperty.setEnableSort(false);
    				tableProperty.setRowIndexStauts(false);
					tableProperty.addColumn("??????", "serial");
					//    				tableProperty.addColumn("?????????(??????)", "buyername", "doModify", linkparam, "buyername", null);
					tableProperty.addColumn("?????????(??????)", "buyername");
					//    				tableProperty.addColumn("??????", "buyer_nationality_dict_name");
					//    				tableProperty.addColumn("????????????", "buyer_province_dict_name");
					tableProperty.addColumn("??????/??????", "buyer_type_dict_name");
					//    				tableProperty.addColumn("??????", "buyer_sex_dict_name");
					//    				tableProperty.addColumn("???????????????", "buyerbirth", "Date", "yyyy-MM-dd", null);
					//    				tableProperty.addColumn("??????(???)", "buyeraddress");
					tableProperty.addColumn("??????", "buyerpostcode");
					tableProperty.addColumn("????????????", "buyer_cardname_dict_name");
					tableProperty.addColumn("????????????", "buyercardcode");
					tableProperty.addColumn("????????????", "buyercall");
					tableProperty.addColumn("??????/???????????????", "buyerproxy");
					tableProperty.addColumn("??????(???)", "buyerproxyadr");
					tableProperty.addColumn("????????????", "buyerproxycall");
					tableProperty.addColumn("????????????", "signFlagStr");
					//    				tableProperty.addColumn("????????????", "????????????", "doSendSms", linkparam, "????????????", null);
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
	 * ???????????????????????????
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
		map.put("msg", "?????????????????????");
        try{
            String contractID = request.getParameter("contractID");
            String serialStr = request.getParameter("serial");
            ContractSignPdfVO cpvo = (ContractSignPdfVO) cpBo.find(ContractSignPdfVO.class, Long.parseLong(contractID));
			if(cpvo == null){
				map.put("result", "fail");
				map.put("msg", "???????????? ??????pdf???");
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
						if(map2.get(i) == 0){//??????
            				map.put("result", "fail");
							map.put("msg", "???" + i + "???????????????????????????,???????????????????????????");
            				return JSONArray.fromObject(map);
            			}
            		}else if (i == serial) {
            			if(map2.get(i) == 0){
            				int random = (int)((Math.random()*9+1)*100000);
							//??????????????????
            				BuyerInfoVO buyer = (BuyerInfoVO)buyerMap.get(i);
            				if(buyer.getVerifyCode() != null && !"".equals(buyer.getVerifyCode())){
								//???????????????
								map.put("result", "fail");
								map.put("msg", "?????????????????????,?????????????????????");
                				return JSONArray.fromObject(map);
            				}
            				DocMessageVO message = new DocMessageVO();
            				message.setTransactionID(Long.parseLong(contractID));
    						message.setFlag(11);
    						message.setMobile(buyer.getBuyerCall());
    						message.setName(buyer.getBuyerName());
    						message.setContent(Constant.SmsURL+"?contractID="+contractID+"&cardNO="+buyer.getBuyerCardcode()+"&code="+random);
    						message.setWriteTime(DateUtil.getSysDate());
							message.setStatus(1);//1?????????
							long count = finder.addSignSms(message);
            				if(count > 0){
								//?????????????????????
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
							//???????????????
							map.put("result", "fail");
							map.put("msg", "???????????????");
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
			map.put("msg", "??????????????????");
            json = JSONArray.fromObject(map);
            return json;
        }
    }
    
    /*
	 * ????????????????????????????????????
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
				//??????????????????????????????
				boolean haveSign = false;
				List<BuyerInfoVO> buyerList = cqBo.searchBuyerInfo(String.valueOf(contractID));
				if(buyerList != null && buyerList.size() > 0){
					for (BuyerInfoVO buyerInfoVO : buyerList) {
						if(buyerInfoVO.getSignFlag() == 1){//?????????
							haveSign = true;
						}
					}
				}
				if(haveSign){
					map.put("result", "2");//?????????????????????
				}else{
					map.put("result", "3");//?????????????????????
				}
			}
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("??????[" + contractID + "]??????????????????????????????    " + DateUtil.getSysDate());
			map.put("result", "-1");
			map.put("msg", "???????????????");
			return JSONArray.fromObject(map);
		}
		return JSONArray.fromObject(map);
	}
	
	
	/*
	 * ?????????????????????????????????
	 */
	@RequestMapping("/deleteSignContract")
	@ResponseBody
	public JSONArray deleteSignContract(HttpServletRequest request,HttpSession session,HttpServletResponse response) {
		Map<String , Object> map = new HashMap<String , Object>();
		map.put("result", "1");
		map.put("msg", "");
		String contractID = request.getParameter("contractID");
		try{
			String flag = request.getParameter("flag");//1????????????????????????0???????????????
			ContractPdfBO cpBo = new ContractPdfBO();
			ContractSignPdfVO cpvo = (ContractSignPdfVO) cpBo.find(ContractSignPdfVO.class, Long.parseLong(contractID));
			if(cpvo != null){
				//??????????????????
				List<MetaFilter> whereFields = new ArrayList<MetaFilter>();
				whereFields.add(new MetaFilter("contractID", "=", contractID));
				cpBo.delete(ContractSignPdfVO.class, whereFields);
				
				//??????????????????
				if(flag != null && !"".equals(flag) && "1".equals(flag)){
					cpBo.updateBuyerInfo(Long.parseLong(contractID));
				}
			}
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("??????????????????[" + contractID + "]???????????????    " + DateUtil.getSysDate());
			map.put("result", "-1");
			map.put("msg", "?????????????????????????????????");
			return JSONArray.fromObject(map);
		}
		return JSONArray.fromObject(map);
	}

}
