package com.netcom.nkestate.fhhouse.salecontract.action;

import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.netcom.nkestate.common.BaseAction;
import com.netcom.nkestate.common.Constant;
import com.netcom.nkestate.fhhouse.FHConstant;
import com.netcom.nkestate.fhhouse.company.vo.EnterpriseQualifyVO;
import com.netcom.nkestate.fhhouse.company.vo.SignerVO;
import com.netcom.nkestate.fhhouse.interfaces.action.RealestateFinder;
import com.netcom.nkestate.fhhouse.interfaces.bo.RealestateBO;
import com.netcom.nkestate.fhhouse.interfaces.external.ZBReader;
import com.netcom.nkestate.fhhouse.interfaces.vo.CHBuildingVO;
import com.netcom.nkestate.fhhouse.interfaces.vo.CHFlatVO;
import com.netcom.nkestate.fhhouse.interfaces.vo.CHLocationVO;
import com.netcom.nkestate.fhhouse.interfaces.vo.CHLotVO;
import com.netcom.nkestate.fhhouse.interfaces.vo.DocMessageVO;
import com.netcom.nkestate.fhhouse.manage.vo.HMFCityVO;
import com.netcom.nkestate.fhhouse.manage.vo.HouseBandAverageVO;
import com.netcom.nkestate.fhhouse.manage.vo.PresellDistrictidManageVO;
import com.netcom.nkestate.fhhouse.manage.vo.PresellManageEndVO;
import com.netcom.nkestate.fhhouse.manage.vo.PresellManageVO;
import com.netcom.nkestate.fhhouse.project.vo.BuildingVO;
import com.netcom.nkestate.fhhouse.project.vo.CompCancelPwdVO;
import com.netcom.nkestate.fhhouse.project.vo.HouseVO;
import com.netcom.nkestate.fhhouse.project.vo.ProPreBldSignerVO;
import com.netcom.nkestate.fhhouse.project.vo.ProjectVO;
import com.netcom.nkestate.fhhouse.project.vo.StartUnitVO;
import com.netcom.nkestate.fhhouse.query.bo.ContractPdfBO;
import com.netcom.nkestate.fhhouse.query.bo.ContractQueryBO;
import com.netcom.nkestate.fhhouse.query.vo.ContractSignPdfVO;
import com.netcom.nkestate.fhhouse.salecontract.bo.SignContractBO;
import com.netcom.nkestate.fhhouse.salecontract.vo.Attach4HireVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.Attach4LimitVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.Attach4OtherVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.Attach4RealVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.Attach4VO;
import com.netcom.nkestate.fhhouse.salecontract.vo.AttachTemplateVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.AttachVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.BuyerInfoVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.ContractCancelPwdVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.ContractDealVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.ContractDetailCsVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.ContractDetailYsVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.ContractTemplateCsVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.ContractTemplateYsVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.EarnestContractVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.PresellAttach2MoneyVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.PresellManageContractConfirVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.SellerInfoVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.XgBuyerOwnerNameVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.XgLimitSaleContractVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.XgTempBuyerInfoVO;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.dao.MetaField;
import com.netcom.nkestate.framework.dao.MetaFilter;
import com.netcom.nkestate.framework.dao.MetaOrder;
import com.netcom.nkestate.framework.html.HtmlTableUtil;
import com.netcom.nkestate.framework.html.TableProperty;
import com.netcom.nkestate.framework.util.DateUtil;
import com.netcom.nkestate.framework.util.VOUtil;
import com.netcom.nkestate.framework.vo.DictVO;
import com.netcom.nkestate.system.bo.DictionaryBO;
import com.netcom.nkestate.system.bo.SystemBO;
import com.netcom.nkestate.system.vo.SmUserVO;

@Controller
@RequestMapping("/outer/signcontract")
public class SignContractAction extends BaseAction {
    static Logger logger = Logger.getLogger(SignContractAction.class.getName());

    /**
	 * ???????????????????????????
	 * @return
	 */
    @RequestMapping("/querySignContract")
    public String querySignContract() {
        try{
            return "/fhhouse/salecontract/signcontract/SignContractQuery";
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
    @RequestMapping("/doQueryCheck")
    @ResponseBody
    public JSONArray delContractID(HttpServletRequest request,HttpSession session) {
        JSONArray json = new JSONArray();
        Map<String , Object> map = new HashMap<String , Object>();
        SmUserVO uservo = (SmUserVO) session.getAttribute("LgUser");
        SignContractBO scBO = new SignContractBO();
        String buildingID = request.getParameter("buildingID");
        try{
            List<MetaFilter> params1 = new ArrayList<MetaFilter>();
            params1.add(new MetaFilter("building_ID", "=", buildingID));
            params1.add(new MetaFilter("signer_ID", "=", uservo.getUserId()));
            List<ProPreBldSignerVO> list1 = scBO.search(ProPreBldSignerVO.class, params1);
            if(list1 != null && list1.size() > 0){
                BuildingVO bvo = (BuildingVO) scBO.find(BuildingVO.class, Long.parseLong(buildingID));
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
    @RequestMapping("/querySignContractList")
    public String querySignContractList(HttpServletRequest request,HttpSession session) {
        try{
            SmUserVO uservo = (SmUserVO) session.getAttribute("LgUser");
            SignContractBO scBO = new SignContractBO();
            RealestateBO rBo = new RealestateBO();
            String projectID=request.getParameter("projectID");
            String startID=request.getParameter("startID");
            String buildingID=request.getParameter("buildingID");
            List<HouseVO> list = scBO.queryUserHouseList(uservo.getUserId(), startID, buildingID);
            if(list != null && list.size() > 0){
                for(HouseVO vo : list){
					//????????????????????????????????????????????????
                    if(vo.getStatus() == FHConstant.HOUSE_STATUS_CANSELL && vo.getManual_Status() == FHConstant.MANUAL_STATUS_CANNOTSELL){
                        vo.setStatus(FHConstant.HOUSE_STATUS_UNACTIVE);
                    }
                }
            }

            //HouseBO hBo = new HouseBO();
            List<HouseVO> list1 = rBo.findFloor(buildingID);
            request.setAttribute("list1", list1);
            request.setAttribute("list", list);
            request.setAttribute("projectID", projectID);
            request.setAttribute("startID", startID);
            return "/fhhouse/salecontract/signcontract/SignContractQueryList";
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
    @RequestMapping("/doHouseView")
    public String doHouseView(HttpServletRequest request,HttpSession session) {
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

			/* ????????????????????????????????? */
            CHFlatVO landInfo = rBo.findlandInfo(houseId);
            request.setAttribute("landInfo", landInfo);
			/* ???????????? */
            List<CHFlatVO> arList = rBo.findRealInfo(houseId);
            request.setAttribute("arList", arList);
			/* ????????????(????????????) */
            List<CHFlatVO> aoList = rBo.findOtherInfo(houseId);
            request.setAttribute("aoList", aoList);
			/* ???????????? */
            List<CHFlatVO> alList = rBo.findLimitInfo(houseId);
            request.setAttribute("alList", alList);
			/* ???????????? */
            List<CHFlatVO> ahList = rBo.findHireInfo(houseId);
            request.setAttribute("ahList", ahList);
			/* ??????????????? */
            List<HouseVO> permitList = rBo.queryPermitInfo(houseId);

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
            request.setAttribute("permitList", permitList);

            request.setAttribute("ctype", ctype);
            request.setAttribute("houseId", houseId);
            request.setAttribute("projectID", projectID);
            request.setAttribute("startID", startID);
            return "/fhhouse/salecontract/signcontract/HouseInfo";
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
    @RequestMapping("/signContractCheck")
    @ResponseBody
    public JSONArray signContractCheck(HttpServletRequest request,HttpSession session) {
        JSONArray json = new JSONArray();
        Map<String , Object> map = new HashMap<String , Object>();
        try{
            SmUserVO uservo = (SmUserVO) session.getAttribute("LgUser");
            String type = request.getParameter("type");
            String startId = request.getParameter("startID");
            String houseId = request.getParameter("houseId");
            String signerId = String.valueOf(uservo.getUserId());
            SignContractBO sBo = new SignContractBO();
            RealestateBO rBo = new RealestateBO();
            int startResult = sBo.checkHouseExist(startId, houseId);
            if(startResult > 0){
                int signerResult = sBo.checkSignerHouseRelate(signerId, houseId);
                if(signerResult > 0){
                    CHFlatVO chFlatVO = (CHFlatVO) rBo.find(CHFlatVO.class, Long.parseLong(houseId));
                    map.put("result", "success");
                    map.put("message", "");

                    if(chFlatVO != null){
                        HouseVO hvo = (HouseVO) sBo.find(HouseVO.class, Long.parseLong(houseId));
						if("1".equals(type)){ //????????????
                            PresellDistrictidManageVO pdmVo = (PresellDistrictidManageVO) sBo.find(PresellDistrictidManageVO.class, hvo.getDistrictID());
							//????????????????????????????????????????????????????????????
                            if(pdmVo != null && pdmVo.getState() == 1){
                                List<MetaFilter> params = new ArrayList<MetaFilter>();
                                params.add(new MetaFilter("building_ID", "=", hvo.getBuilding_ID()));
                                List<PresellManageVO> pmlist = sBo.search(PresellManageVO.class, params);
                                //PresellManageVO pmVo = (PresellManageVO) sBo.find(PresellManageVO.class, );
                                if(pmlist != null && pmlist.size() > 0){
                                    map.put("result", "success");
                                    map.put("message", "");
                                }else{
                                    map.put("result", "fail");
									map.put("message", "????????????????????????????????????????????????????????????!");
                                }
                            }else{
                                //map.put("result", "fail");
								//map.put("message", "???????????????????????????????????????????????????!");
                                map.put("result", "success");
                                map.put("message", "");
                            }
                        }else{
                            map.put("result", "success");
                            map.put("message", "");
                        }
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
	 * ??????????????????????????????ID?????????
	 * @param request
	 * @param session
	 * @return
	 */
    @RequestMapping(value = "/findTemplate")
    @ResponseBody
    public JSONArray findTemplate(HttpServletRequest request,HttpSession session) {
        JSONArray json = new JSONArray();
        try{
            String projectID = request.getParameter("projectID");
            String startID = request.getParameter("startID");
            String type = request.getParameter("type");
            SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
            String district = this.getUserSqlDistricts(vo.getRegionId());

            SignContractBO scBO = new SignContractBO();
            List<DictVO> list = scBO.queryTemplateList(projectID, startID, district, type);
            json = JSONArray.fromObject(list);
            return json;
        }catch (Exception e){
            e.printStackTrace();
            return json;
        }
    }

    /**
	 * ?????????????????????????????????
	 * @param request
	 * @return
	 */
    @RequestMapping(value = "/createContract")
    public String createContract(HttpServletRequest request,HttpSession session) {
        try{
            SignContractBO scBO = new SignContractBO();
			String type = request.getParameter("type");
            String projectID = request.getParameter("projectID");
            String startID = request.getParameter("startID");
            String houseId = request.getParameter("houseId");
            String earnestID = request.getParameter("earnestID");
            SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
            String templateID = request.getParameter("template");
            if(templateID==null || "0".equals(templateID)|| "-1".equals(templateID)|| "null".equalsIgnoreCase(templateID)){
            	templateID = "";
            }
            
            String person = vo.getLoginName();
            long dates = Long.parseLong(DateUtil.getSysDateYYYYMMDD());
            long times = Long.parseLong(DateUtil.getSysDateHHMMSS());

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
            //siVo.setSellerProxy(eqvo.getProxy());
            //siVo.setSellerProxyCall(eqvo.getProxy_Call());
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
            cdVo.setTemplateId(templateID);
            
            
            RealestateBO rBo = new RealestateBO();
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
            if(type != null && "1".equals(type)){
                cdVo.setContractversion(FHConstant.NEW_PC_CONTRACTVERSION);
				//????????????
                ContractDetailYsVO pcVo = new ContractDetailYsVO();
                pcVo.setContractID(Long.parseLong(new SystemBO().getSequence("CONTRACTID")));
                pcVo.setCrePerson(person);
                pcVo.setCreDate(dates);
                pcVo.setCreTime(times);
                pcVo.setUpdPerson(person);
                pcVo.setUpdDate(dates);
                pcVo.setUpdTime(times);
				//????????????
                int attachSerial[] = { FHConstant.ATTACH_SERIAL_PFJ2, FHConstant.ATTACH_SERIAL_PFJ3, FHConstant.ATTACH_SERIAL_PFJ4, FHConstant.ATTACH_SERIAL_PFJ5, FHConstant.ATTACH_SERIAL_PFJ8, FHConstant.ATTACH_SERIAL_PFJ9, FHConstant.ATTACH_SERIAL_PFJ10, FHConstant.ATTACH_SERIAL_PFJ11 };
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
                if(earnestID != null && !"".equals(earnestID)){
                    pcVo.setEarnestID(earnestID);
                }
                contractID = scBO.addSignContract(pcVo, null, null, siVo, null, cdVo, type, attachList, person, dates, times);
            }else if(type != null && "2".equals(type)){
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
                if(earnestID != null && !"".equals(earnestID)){
                    scVo.setEarnestID(earnestID);
                }
                contractID = scBO.addSignContract(null, scVo, null, siVo, null, cdVo, type, attachList, person, dates, times);
            }else if(type != null && "3".equals(type)){
                cdVo.setContractversion(FHConstant.EC_CONTRACTVERSION);
				//????????????
                EarnestContractVO ecvo = new EarnestContractVO();
                ecvo.setContractID(Long.parseLong(new SystemBO().getSequence("CONTRACTID")));
                ecvo.setCrePerson(person);
                ecvo.setCreDate(dates);
                ecvo.setCreTime(times);
                ecvo.setUpdPerson(person);
                ecvo.setUpdDate(dates);
                ecvo.setUpdTime(times);

                contractID = scBO.addSignContract(null, null, ecvo, siVo, null, cdVo, type, null, person, dates, times);
            }

            if(contractID > 0){
                request.setAttribute("contractID", contractID);
                request.setAttribute("type", type);
                request.setAttribute("houseId", houseId);
                request.setAttribute("earnestID", earnestID);
                return "/fhhouse/salecontract/signcontract/ContractCreate";
            }else{
                return ERROR_System;
            }
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
    @RequestMapping(value = "/editBuyer1")
    public String editBuyer1(HttpServletRequest request,Page page,HttpSession session) {
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
                List<BuyerInfoVO> buyerList = scBO.search(BuyerInfoVO.class, params, orders, page);
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
                    linkparam.add("contractid");
                    linkparam.add("serial");

					/** ??????table?????? */
                    TableProperty tableProperty = new TableProperty();
                    tableProperty.setEnableSort(false);
                    tableProperty.setRowIndexStauts(false);
					tableProperty.addColumn("??????", "serial");
					tableProperty.addColumn("?????????(??????)", "buyername", "doModify", linkparam, "buyername", null);
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
                    String htmlView = HtmlTableUtil.createHtmlTableForEasyUI(tableProperty, buyerList, page, contractID);

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
	 * ????????????????????????????????????????????????
	 * @param request
	 * @return
	 */
    @RequestMapping(value = "/operaBuyerInfo")
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

                ContractCancelPwdVO ccpVo = new ContractCancelPwdVO();
                ccpVo.setContractID(Long.parseLong(contractID));
                ccpVo.setSerial(Integer.parseInt(serial));
                ccpVo = (ContractCancelPwdVO) scBO.find(ccpVo);
                if(ccpVo != null){
                    request.setAttribute("passwd", ccpVo.getPwd());
                }
            }
            return "/fhhouse/salecontract/signcontract/BuyerInfoSave";
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
    @RequestMapping(value = "/saveBuyerInfo")
    @ResponseBody
    public JSONArray saveBuyerInfo(HttpServletRequest request,HttpSession session,BuyerInfoVO biVo) {
        JSONArray json = new JSONArray();
        Map<String , Object> map = new HashMap<String , Object>();
        try{
            String passwd = request.getParameter("passwd");
            SignContractBO scBO = new SignContractBO();
            SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
            int serial = biVo.getSerial();
            long contractID = biVo.getContractID();
            boolean res = false;
            if(contractID > 0){
				if(1 != 1){
					/*** 20181120 added by gcf ?????????????????????????????????????????????????????????????????????????????????-start ****/
                ZBReader reader = new ZBReader();
                HashMap resultMap = reader.getToken(Constant.username, Constant.password);
                if(resultMap != null){
						if("1".equals(resultMap.get("result").toString())){//?????????
							String token = resultMap.get("token").toString();
                        resultMap = reader.getZBHouseInfo(biVo.getBuyerName(), biVo.getBuyerCardcode(), token);
                        if(resultMap != null){
								if("1".equals(resultMap.get("result").toString())){//????????????1??????????????????0
                                String data = resultMap.get("data").toString();
                                String arr[] = data.split(",");
                                if(arr != null && arr.length > 0){
                                    String distName = scBO.getDistNameForZB(arr[0].substring(0, 2).toUpperCase());
                                    map.put("result", "fail");
										map.put("msg", "????????????????????????" + biVo.getBuyerCardcode() + "????????????????????????" + arr[0] + "???????????????????????????????????????" + distName + "?????????????????????????????????????????????????????????");
                                    json = JSONArray.fromObject(map);
                                    return json;
                                }
                            }
							}else{//??????
                            map.put("result", "fail");
								map.put("msg", "??????????????????????????????????????????");
                            json = JSONArray.fromObject(map);
                            return json;
                        }
                    }
                }else{
                    map.put("result", "fail");
						map.put("msg", "?????????????????????????????????");
                    json = JSONArray.fromObject(map);
                    return json;
                }
				}
				/*** 20181120 added by gcf ?????????????????????????????????????????????????????????????????????????????????-end ****/

				//??????????????????
                ContractCancelPwdVO ccpVo = new ContractCancelPwdVO();
                ccpVo.setContractID(biVo.getContractID());
                ccpVo.setPwd(passwd);
                ccpVo.setUpdPerson(vo.getLoginName());
                ccpVo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
                ccpVo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));

				//????????????
                biVo.setUpdPerson(vo.getLoginName());
                biVo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
                biVo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));

                if("-99".equals(biVo.getBuyerProvince())){
                    biVo.setBuyerProvince(null);
                }
                if("-99".equals(biVo.getBuyerSex())){
                    biVo.setBuyerSex(null);
                }


				if(serial != 0){ //?????????????????????????????????????????????????????????
					ccpVo.setSerial(biVo.getSerial());
					//???????????????????????????????????????????????????
					List<MetaFilter> params = new ArrayList<MetaFilter>();
                    params.add(new MetaFilter("contractID", "=", contractID));
                    params.add(new MetaFilter("serial", "=", biVo.getSerial()));
                    List<MetaOrder> orders = new ArrayList<MetaOrder>();
                    List<ContractCancelPwdVO> buyerPwdList = scBO.search(ContractCancelPwdVO.class, params, orders, null);
                    if(buyerPwdList == null || buyerPwdList.size() <= 0){
                        ccpVo.setCrePerson(vo.getLoginName());
                        ccpVo.setCreDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
                        ccpVo.setCreTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
                        res = scBO.saveBuyerInfo(ccpVo, biVo, 2);
                    }else{
                        res = scBO.saveBuyerInfo(ccpVo, biVo, 0);
                    }
                }else{
                    List<MetaFilter> params = new ArrayList<MetaFilter>();
                    params.add(new MetaFilter("contractID", "=", contractID));
                    List<MetaOrder> orders = new ArrayList<MetaOrder>();
                    orders.add(new MetaOrder("serial", MetaOrder.DESC));
					List<BuyerInfoVO> buyerList = scBO.search(BuyerInfoVO.class, params, orders, null); //???????????????????????????????????????????????????
					int serialnext = 1; //???????????????????????????1??????
                    if(buyerList != null && buyerList.size() > 0){
						BuyerInfoVO buyerInfo = buyerList.get(0); //???????????????????????????????????????
						serialnext = buyerInfo.getSerial() + 1; //?????????????????????????????????
					}

                    biVo.setSerial(serialnext);
                    biVo.setCrePerson(vo.getLoginName());
                    biVo.setCreDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
                    biVo.setCreTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));

                    ccpVo.setSerial(serialnext);
                    ccpVo.setCrePerson(vo.getLoginName());
                    ccpVo.setCreDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
                    ccpVo.setCreTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));

                    res = scBO.saveBuyerInfo(ccpVo, biVo, 1);
                }
            }

            if(res == true){
                map.put("result", "success");
				map.put("msg", "?????????????????????");
            }else{
                map.put("result", "fail");
				map.put("msg", "?????????????????????");
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
	 * ???????????????????????????
	 * @param request
	 * @param session
	 * @return
	 */
    @RequestMapping(value = "/deleteBuyer")
    @ResponseBody
    public JSONArray deleteBuyer(HttpServletRequest request) {
        JSONArray json = new JSONArray();
        Map<String , Object> map = new HashMap<String , Object>();
        try{
            String contractID = request.getParameter("contractID");
            String serial = request.getParameter("serial");
            SignContractBO scBO = new SignContractBO();
            if(contractID != null && !"".equals(contractID) && serial != null && !"".equals(serial)){
                BuyerInfoVO biVo = new BuyerInfoVO();
                biVo.setContractID(Long.parseLong(contractID));
                biVo.setSerial(Integer.parseInt(serial));

                ContractCancelPwdVO ccpVo = new ContractCancelPwdVO();
                ccpVo.setContractID(Long.parseLong(contractID));
                ccpVo.setSerial(Integer.parseInt(serial));

                boolean res = false;
                res = scBO.deleteBuyerInfo(ccpVo, biVo);
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
	 * ?????????????????????????????????
	 * @param request
	 * @param session
	 * @return
	 */
    @RequestMapping("/gotoSignContract")
    public String gotoSignContract(HttpServletRequest request,HttpSession session) {
        try{
            String url = "";
            SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
            String type = request.getParameter("type");
            String houseId = request.getParameter("houseId");
            String contractID = request.getParameter("contractID");
            String startID = request.getParameter("startID");
            request.setAttribute("houseId", houseId);
            request.setAttribute("contractID", contractID);
            request.setAttribute("startID", startID);
            RealestateBO rBo = new RealestateBO();
            SignContractBO scBO = new SignContractBO();
            DictionaryBO dictBO = new DictionaryBO();

            ContractDealVO cdVO = new ContractDealVO();
            cdVO.setContractID(Long.parseLong(contractID));
            cdVO = (ContractDealVO)scBO.find(cdVO);
			//??????????????????
            ProjectVO projectVO = new ProjectVO();
            projectVO.setProject_ID(cdVO.getProjectID());
            projectVO = (ProjectVO)scBO.find(projectVO);

            String templateID = request.getParameter("template");
            ContractTemplateYsVO ptVo = null;
            ContractTemplateCsVO stVo = null;
            if(templateID != null && !"".equals(templateID) && !"0".equals(templateID) && !"null".equals(templateID)){
				//??????????????????
                List<MetaFilter> para = new ArrayList<MetaFilter>();
                para.add(new MetaFilter("templateID", "=", templateID));
                List<AttachTemplateVO> atList = scBO.search(AttachTemplateVO.class, para);
                Map<String , String> map = new HashMap<String , String>();
                if(atList != null && atList.size() > 0){
                    for(AttachTemplateVO attachTemplateVO : atList){
                        map.put(attachTemplateVO.getSerial() + "", attachTemplateVO.getContent() == null ? "" : new String(attachTemplateVO.getContent()));
                    }
                }
                request.setAttribute("attachmap", map);

				//????????????????????????
				/*
				List<MetaFilter> params = new ArrayList<MetaFilter>();
				params.add(new MetaFilter("templateID", "=", templateID));
				List<SellerTemplateVO> sellerlist = scBO.search(SellerTemplateVO.class, params);
				if(sellerlist != null && sellerlist.size() > 0){
					SellerTemplateVO sellerTemplateVO = sellerlist.get(0);
					request.setAttribute("item", sellerTemplateVO);
					request.setAttribute("seller", "yes");
				}
				*/

                if(type != null && "1".equals(type)){
					//????????????????????????
                    ptVo = (ContractTemplateYsVO) scBO.find(ContractTemplateYsVO.class, Long.parseLong(templateID));
                    //request.setAttribute("ptVo", ptVo);
                }else if(type != null && "2".equals(type)){
					//????????????????????????
                    stVo = (ContractTemplateCsVO) scBO.find(ContractTemplateCsVO.class, Long.parseLong(templateID));
                    //request.setAttribute("stVo", stVo);
                }
            }else{

				/* ???????????? */
                List<MetaFilter> params = new ArrayList<MetaFilter>();
                params.add(new MetaFilter("contractID", "=", contractID));
                List<MetaOrder> orders = new ArrayList<MetaOrder>();
                orders.add(new MetaOrder("serial", MetaOrder.ASC));
                List<BuyerInfoVO> buyerList = scBO.search(BuyerInfoVO.class, params, orders, null);
                request.setAttribute("buyerList", buyerList);
            }
			/* ???????????? */
            List<MetaFilter> params = new ArrayList<MetaFilter>();
            params.add(new MetaFilter("contractID", "=", contractID));
            List<MetaOrder> orders = new ArrayList<MetaOrder>();
            orders.add(new MetaOrder("serial", MetaOrder.ASC));
            List<SellerInfoVO> sellerList = scBO.search(SellerInfoVO.class, params, orders, null);
            request.setAttribute("sellerInfoVO", sellerList.get(0));

			/* ???????????? */
            CHLotVO chLotVO = rBo.findChLot(houseId);
            //if(chLotVO != null){
            //	request.setAttribute("chLotVO", chLotVO);
            //}
			/* ?????? */
            CHFlatVO chFlatVO = (CHFlatVO) rBo.find(CHFlatVO.class, Long.parseLong(houseId));

            CHBuildingVO builddingVO = (CHBuildingVO) rBo.find(CHBuildingVO.class, chFlatVO.getBuildingID());

			/* ???????????? */
            String bulidingMater = dictBO.getDictName("ct_543", builddingVO.getBuilding_Mater());
			//??????????????????
            String issuerDistrictID = chFlatVO.getDistrictID();
            if("0".equals(issuerDistrictID) || "2".equals(issuerDistrictID) || "3".equals(issuerDistrictID) || "5".equals(issuerDistrictID)
                    || "13".equals(issuerDistrictID)){
                issuerDistrictID = "0";
            }
            String issuerName = "";
            if(issuerDistrictID != null && !"".equals(issuerDistrictID) && Integer.parseInt(issuerDistrictID) == 12 && type != null && "1".equals(type)){
				issuerName = "?????????????????????????????????";
            }else{
                issuerName = dictBO.getDictName("ct_510", issuerDistrictID);
            }
            //request.setAttribute("bulidingFloors", builddingVO.getFloors() == null ? "" : builddingVO.getFloors());

            HouseVO hvo = (HouseVO) scBO.find(HouseVO.class, Long.parseLong(houseId));
			/* ???????????? */
            //SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
            //String location = rBo.findLocation(houseId, hvo.getDistrictID());
			//20190813 modify by gcf ?????????????????????????????????????????????ch_flat??????location_name
            String location = chFlatVO.getLocation_Name() != null ? chFlatVO.getLocation_Name():"";    
            //request.setAttribute("location", location);
			/* ???????????? */
            PresellDistrictidManageVO pdmVo = (PresellDistrictidManageVO) scBO.find(PresellDistrictidManageVO.class, hvo.getDistrictID());
			//request.setAttribute("regulator", pdmVo.getRegulator()); //????????????
            //BuildingHouseVO bhVo = (BuildingHouseVO) scBO.find(BuildingHouseVO.class, Long.parseLong(houseId));
            PresellManageVO pmVo = (PresellManageVO) scBO.find(PresellManageVO.class, hvo.getBuilding_ID());
            //request.setAttribute("pmVo", pmVo);

			/* ???????????? */
            List<CHFlatVO> arList = rBo.findRealInfo(houseId);
			/* ????????????(????????????) */
            List<CHFlatVO> aoList = rBo.findOtherInfo(houseId);

			/* ????????????????????????????????? */
            CHFlatVO landInfoVO = rBo.findlandInfo(houseId);

			/* ??????????????? */
            String permitNo = rBo.findPermitNo(houseId);
            request.setAttribute("permitNo", permitNo);
            if("1".equals(type)){
                ContractDetailYsVO ysdVo = new ContractDetailYsVO();
                if(ptVo != null){
                    ContractDetailYsVO tVo = (ContractDetailYsVO) scBO.find(ContractDetailYsVO.class, Long.parseLong(contractID));
                    ysdVo = (ContractDetailYsVO) VOUtil.copyVO(ptVo, ysdVo);
                    ysdVo.setEarnestID(tVo.getEarnestID());
                    ysdVo.setCrePerson(tVo.getCrePerson());
                    ysdVo.setCreDate(tVo.getCreDate());
                    ysdVo.setCreTime(tVo.getCreTime());
                }
                ysdVo.setContractID(Long.parseLong(contractID));
                ysdVo.setUpdPerson(vo.getLoginName());
                ysdVo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
                ysdVo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
				//?????????
				if(landInfoVO != null){
                    ysdVo.setf0101(landInfoVO.getAttribute("landsourceStr")==null?"":landInfoVO.getAttribute("landsourceStr").toString());
                    ysdVo.setf0105(landInfoVO.getAttribute("blockarea2")==null?"":landInfoVO.getAttribute("blockarea2").toString());
                    ysdVo.setf0106(landInfoVO.getAttribute("permitusage")==null?"":landInfoVO.getAttribute("permitusage").toString());
                    ysdVo.setf0107(landInfoVO.getAttribute("enddateStr")==null?"":landInfoVO.getAttribute("enddateStr").toString());
                }
                if(chLotVO!=null){
                    ysdVo.setf0102(chLotVO.getLot_Number());
                }

                if(arList!=null && arList.size()>0){
                    CHFlatVO realVO = arList.get(0);
                    ysdVo.setf0104(realVO.getAttribute("realno").toString());
                }
				ysdVo.setf0103("????????????????????????");
                ysdVo.setf0108(projectVO.getProjectName());

				//?????????
				ysdVo.setf0201(issuerName);
                ysdVo.setf0202(permitNo);

				//?????????
				String landUse = dictBO.getDictName("ct_540", chFlatVO.getHouse_Use());
                ysdVo.setf0301(landUse);
                ysdVo.setf0302(bulidingMater);
                ysdVo.setf0306(location);
                ysdVo.setf0308(chFlatVO.getPlan_Flarea());
                ysdVo.setf0309(chFlatVO.getPlan_Priv_Flarea());
                ysdVo.setf0310(chFlatVO.getPlan_Co_Flarea());

				//?????????
				if(aoList != null && aoList.size() > 0){
					ysdVo.setf0401("??????");
                    CHFlatVO otherVO = aoList.get(0);
                    ysdVo.setf0402(otherVO.getAttribute("otherrighttypeid")==null?"":otherVO.getAttribute("otherrighttypeid").toString());
                    ysdVo.setf0403(otherVO.getAttribute("realName")==null?"":otherVO.getAttribute("realName").toString());
                    ysdVo.setf0404(otherVO.getAttribute("name")==null?"":otherVO.getAttribute("name").toString());
                    ysdVo.setf0405(dictBO.getDictName("ct_513", issuerDistrictID));
                    ysdVo.setf0406(otherVO.getAttribute("passdate")==null?"":otherVO.getAttribute("passdate").toString());
                    String sdate = otherVO.getAttribute("planstartdate")==null?"":otherVO.getAttribute("planstartdate").toString();
                    String edate = otherVO.getAttribute("planenddate")==null?"":otherVO.getAttribute("planenddate").toString();
					ysdVo.setf0407("???" + sdate + "???" + edate + "???");

                    if(aoList.size()>1){
                        otherVO = aoList.get(1);
                        ysdVo.setf0408(otherVO.getAttribute("otherrighttypeid")==null?"":otherVO.getAttribute("otherrighttypeid").toString());
                        ysdVo.setf0409(otherVO.getAttribute("realName")==null?"":otherVO.getAttribute("realName").toString());
                        ysdVo.setf0410(otherVO.getAttribute("name")==null?"":otherVO.getAttribute("name").toString());
                        ysdVo.setf0411(dictBO.getDictName("ct_513", issuerDistrictID));
                        ysdVo.setf0412(otherVO.getAttribute("passdate")==null?"":otherVO.getAttribute("passdate").toString());
                        sdate = otherVO.getAttribute("planstartdate")==null?"":otherVO.getAttribute("planstartdate").toString();
                        edate = otherVO.getAttribute("planenddate")==null?"":otherVO.getAttribute("planenddate").toString();
						ysdVo.setf0413("???" + sdate + "???" + edate + "???");
                    }


                }else{
					ysdVo.setf0401("?????????");
                }

				//?????????,????????????????????????
                if(pdmVo != null && pdmVo.getState() == 1){
                    ysdVo.setf0725(pdmVo.getRegulator());
                    if(pmVo != null && pmVo.getState() > 0){
                        ysdVo.setf0726(pmVo.getManage_Acct_Name());
                        ysdVo.setf0727(pmVo.getManage_Acct());
                    }
                }

                scBO.update(ysdVo);
                request.setAttribute("ysdVo", ysdVo);
                url = "/fhhouse/salecontract/signcontract/PresellContractSign";
            }
            if("2".equals(type)){
                ContractDetailCsVO csdVo = new ContractDetailCsVO();
                if(stVo != null){
                    ContractDetailCsVO tVo = (ContractDetailCsVO) scBO.find(ContractDetailCsVO.class, Long.parseLong(contractID));
                    csdVo = (ContractDetailCsVO) VOUtil.copyVO(stVo, csdVo);
                    csdVo.setEarnestID(tVo.getEarnestID());
                    csdVo.setCrePerson(tVo.getCrePerson());
                    csdVo.setCreDate(tVo.getCreDate());
                    csdVo.setCreTime(tVo.getCreTime());
                }
                csdVo.setContractID(Long.parseLong(contractID));
                csdVo.setUpdPerson(vo.getLoginName());
                csdVo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
                csdVo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
				//?????????
				if(landInfoVO != null){
                    csdVo.setf0101(landInfoVO.getAttribute("landsourceStr") == null ? "" : landInfoVO.getAttribute("landsourceStr").toString());
                    csdVo.setf0105(landInfoVO.getAttribute("blockarea2") == null ? "" : landInfoVO.getAttribute("blockarea2").toString());
                    csdVo.setf0106(landInfoVO.getAttribute("permitusage") == null ? "" : landInfoVO.getAttribute("permitusage").toString());
                    csdVo.setf0107(landInfoVO.getAttribute("enddateStr") == null ? "" : landInfoVO.getAttribute("enddateStr").toString());
                }
                if(chLotVO != null){
                    csdVo.setf0102(chLotVO.getLot_Number());
                }
				csdVo.setf0103("????????????????????????");
                csdVo.setf0108(projectVO.getProjectName());

				//?????????
				csdVo.setf0205(issuerName);
                if(arList != null && arList.size() > 0){
                    CHFlatVO realVO = arList.get(0);
                    csdVo.setf0203(realVO.getAttribute("realno").toString());
                }

				//?????????
				String landUse = dictBO.getDictName("ct_540", chFlatVO.getHouse_Use());
                csdVo.setf0301(landUse);
                csdVo.setf0302(bulidingMater);
                csdVo.setf0306(location);
                csdVo.setf0308(chFlatVO.getFlarea());
                csdVo.setf0309(chFlatVO.getPriv_Flarea());
                csdVo.setf0310(chFlatVO.getCo_Flarea());

				//?????????
				if(aoList != null && aoList.size() > 0){
					csdVo.setf0401("??????");
                    CHFlatVO otherVO = aoList.get(0);
                    csdVo.setf0402(otherVO.getAttribute("realName") == null ? "" : otherVO.getAttribute("realName").toString());
                    csdVo.setf0403(otherVO.getAttribute("name") == null ? "" : otherVO.getAttribute("name").toString());
                    csdVo.setf0404(dictBO.getDictName("ct_513", issuerDistrictID));
                    csdVo.setf0405(otherVO.getAttribute("passdate") == null ? "" : otherVO.getAttribute("passdate").toString());
                    String sdate = otherVO.getAttribute("planstartdate") == null ? "" : otherVO.getAttribute("planstartdate").toString();
                    String edate = otherVO.getAttribute("planenddate") == null ? "" : otherVO.getAttribute("planenddate").toString();
					csdVo.setf0406("???" + sdate + "???" + edate + "???");

                }else{
					csdVo.setf0401("?????????");
                }

                scBO.update(csdVo);
                request.setAttribute("csdVo", csdVo);
                url = "/fhhouse/salecontract/signcontract/SellContractSign";
            }
            if("3".equals(type)){
				/* ???????????? */
                String projectID = request.getParameter("projectID");
                ProjectVO pvo = (ProjectVO) scBO.find(ProjectVO.class, Long.parseLong(projectID));
                request.setAttribute("projectName", pvo.getProjectName());
				/* ?????????????????? */
                CHLocationVO chvo = rBo.findLocationInfo(houseId);
                request.setAttribute("chvo", chvo);
                if(chFlatVO != null){
                    request.setAttribute("roomNumber", chFlatVO.getRoom_Number());
                    request.setAttribute("area", chFlatVO.getFlarea() == null ? chFlatVO.getFlarea() : chFlatVO.getFlarea());
                    request.setAttribute("planarea", chFlatVO.getPlan_Flarea() == null ? chFlatVO.getPlan_Flarea() : chFlatVO.getPlan_Flarea());
                    if(arList != null && arList.size() > 0){
                        CHFlatVO realVO = arList.get(0);
                        request.setAttribute("realno", realVO.getAttribute("realno"));
                    }
                }
                url = "/fhhouse/salecontract/signcontract/EarnestContractSign";
            }
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
    @RequestMapping(value = "/gotoXgCheck")
    @ResponseBody
    public JSONArray gotoXgCheck(HttpServletRequest request,HttpSession session) {
        JSONArray json = new JSONArray();
        Map<String , Object> map = new HashMap<String , Object>();
        SignContractBO scBO = new SignContractBO();
        RealestateBO estateBO = new RealestateBO();
        try{
            String houseId = request.getParameter("houseId");
			//????????????????????????
            long nState = scBO.findState(houseId);
			boolean isXG = true;//????????????
			//1?????????????????????
			if(nState == 1){
                HouseVO hvo = (HouseVO) scBO.find(HouseVO.class, Long.parseLong(houseId));
				//2?????????????????????
				if(hvo.getNoXgState() == 0){
					//???????????????????????????
					CHFlatVO flatvo = estateBO.findCHFlat(Long.parseLong(houseId));
                    if(flatvo!=null){
						//3??????????????????????????????
                        if("1".equals(flatvo.getFlat_Type())){
                            String houseType = flatvo.getAttribute("house_type") == null ? "" : flatvo.getAttribute("house_type").toString();
                            if(!"".equals(houseType) && houseType != null){
                                int ht = Integer.parseInt(houseType);
								//4????????????????????????
                                int notXGType[] = { 1, 3, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14, 15 };
                                for(int it : notXGType){
                                    if(ht == it){
                                        isXG = false;
                                        break;
                                    }
                                }
                            }
                        }else{
                            isXG = false;
                        }
                    }

                }else{
                    isXG = false;
                }

            }else{
                isXG = false;
            }

            if(isXG){
                map.put("result", "fail");
                map.put("msg", "");
            }else{
                map.put("result", "success");
				map.put("msg", "?????????????????????");
            }
            json = JSONArray.fromObject(map);
            return json;
        }catch (Exception e){
            e.printStackTrace();
            map.put("result", "fail");
			map.put("msg", "??????????????????!");
            json = JSONArray.fromObject(map);
            return json;
        }
    }

    /**
	 * ?????????????????????????????????
	 * @return
	 */
    @RequestMapping("/gotoXgView")
    public String gotoXgInfo(HttpServletRequest request,HttpSession session) {
        try{
            SignContractBO scBO = new SignContractBO();
            String contractID = request.getParameter("contractID");
            String houseID = request.getParameter("houseID");
            List<XgTempBuyerInfoVO> list = scBO.queryLocalXgInfo(contractID);
            request.setAttribute("list", list);
            request.setAttribute("contractID", contractID);
            request.setAttribute("houseID", houseID);
            return "/fhhouse/salecontract/signcontract/XgInfoQuery";
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
    @RequestMapping("/queryXgInfo")
    public String queryXgInfo(HttpServletRequest request,HttpSession session) {
        try{
            String xgID = request.getParameter("xgID");
            String houseID = request.getParameter("houseID");
            String contractID = request.getParameter("contractID");
            RealestateBO rBO = new RealestateBO();

			//????????????????????????
            List<CHLotVO> xgList = rBO.queryXgInfo(xgID);
            if(xgList != null && xgList.size() > 0){
                List<CHLotVO> xgHumanList = rBO.queryXgHumanInfo(xgID);
                if(xgHumanList != null && !xgHumanList.isEmpty()){
                    List<String> linkparam = new ArrayList<String>();
                    linkparam.add("xgid");
					/** ??????table?????? */
                    TableProperty tableProperty = new TableProperty();
                    tableProperty.setEnableSort(false);
                    tableProperty.setRowIndexStauts(true);
					tableProperty.addColumn("??????", "name");
					tableProperty.addColumn("????????????", "cardno");
					tableProperty.addColumn("??????ID", "xgid", true);
                    String htmlView = HtmlTableUtil.createHtmlTable(tableProperty, xgHumanList);
                    request.setAttribute("xgID", xgID);
                    request.setAttribute("contractID", contractID);
                    request.setAttribute("houseID", houseID);
                    request.setAttribute("htmlView", htmlView);
                }
            }
            return "/fhhouse/salecontract/signcontract/XgInfoQuery";
        }catch (Exception e){
            e.printStackTrace();
            return ERROR_System;
        }
    }

    /**
	 * ???????????????????????????????????????
	 * @param request
	 * @return
	 */
    @RequestMapping("/delXgInfo")
    @ResponseBody
    public JSONArray delXgInfo(HttpServletRequest request) {
        JSONArray json = new JSONArray();
        Map<String , Object> map = new HashMap<String , Object>();
        String contractID = request.getParameter("contractID");
        try{
            SignContractBO scBO = new SignContractBO();
            List<MetaFilter> params = new ArrayList<MetaFilter>();
            params.add(new MetaFilter("contractID", "=", contractID));
            int result1 = scBO.deleteLocalXgInfo(contractID);
            int result2 = scBO.deleteLocalHumanXgInfo(contractID);
            long count = scBO.delete(XgLimitSaleContractVO.class, params);
            if(count > 0 && result1 > 0 && result2 > 0){
                map.put("result", "success");
				map.put("message", "???????????????????????????");
            }else{
                map.put("result", "fail");
				map.put("message", "???????????????????????????");
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
	 * ?????????????????????????????????????????????
	 * @param request
	 * @return
	 */
    @RequestMapping(value = "/gotoSubmitXgInfoCheck")
    @ResponseBody
    public JSONArray gotoSubmitXgInfoCheck(HttpServletRequest request,HttpSession session) {
        JSONArray json = new JSONArray();
        Map<String , Object> map = new HashMap<String , Object>();
        SignContractBO scBO = new SignContractBO();
        RealestateBO rBO = new RealestateBO();
        try{
            String xgID = request.getParameter("xgID");
            String houseId = request.getParameter("houseID");
            String contractID = request.getParameter("cID");

			//???????????????????????????????????????????????????
			List<MetaFilter> params = new ArrayList<MetaFilter>();
            params.add(new MetaFilter("limitSaleID", "=", xgID));
            List<XgLimitSaleContractVO> list1 = scBO.search(XgLimitSaleContractVO.class, params);

            String msg = "";
            if(list1 != null && list1.size() > 0){
				msg = "??????????????????????????????????????????";
            }
            if("".equals(msg)){
				//???????????????????????????????????????????????????
				List shList = scBO.findSHXgTransList(Long.parseLong(xgID));
                if(shList != null && shList.size() > 0){
					msg = "????????????????????????????????????????????????";
                }
            }
            if("".equals(msg)){
				//???????????????????????????????????????????????????
				List bdcList = rBO.findBdcXgTransList(Long.parseLong(xgID));
                if(bdcList != null && bdcList.size() > 0){
					msg = "??????????????????????????????????????????????????????";
                }
            }
            if("".equals(msg)){
                List<CHLotVO> xgList = rBO.queryXgInfo(xgID);
                if(xgList != null && xgList.size() > 0){
                    CHLotVO chLotVO = (CHLotVO) xgList.get(0);
                    String houseDistrictid = chLotVO.getAttribute("housedistrictid").toString();
                    HouseVO hvo = (HouseVO) scBO.find(HouseVO.class, Long.parseLong(houseId));
                    String[] arr = { "2", "3", "5", "13", "21" };
					//???????????????????????????
					if(Arrays.asList(arr).contains(String.valueOf(hvo.getDistrictID()))){
						//?????????????????????????????????????????????
						if(!Arrays.asList(arr).contains(houseDistrictid)){
							msg = "???????????????????????????????????????????????????????????????????????????????????????????????????!";
                        }
                    }else{
						//???????????????????????????????????????????????????
						if(!String.valueOf(hvo.getDistrictID()).equals(houseDistrictid)){
							msg = "???????????????????????????????????????????????????????????????????????????????????????????????????!";
                        }
                    }

                    //if( ("0".equals(houseDistrictid) && !Arrays.asList(arr).contains(hvo.getDistrictID())) || !String.valueOf(hvo.getDistrictID()).equals(houseDistrictid)){
					//	msg = "???????????????????????????????????????????????????????????????????????????????????????????????????!";
                    //}
                    if("".equals(msg)){
						//2020.3.23 ph ??????????????????????????????????????????????????????
                    	long xgManCheckState = scBO.findxgManCheckState(houseId);
						/* ???????????? */
                        List<MetaFilter> params1 = new ArrayList<MetaFilter>();
                        params1.add(new MetaFilter("contractID", "=", contractID));
                        if(xgManCheckState==1){
                        	params1.add(new MetaFilter("buyerType", "=", 0));
                        }
                        List<MetaOrder> orders1 = new ArrayList<MetaOrder>();
                        orders1.add(new MetaOrder("serial", MetaOrder.ASC));
                        List<BuyerInfoVO> buyerList = scBO.search(BuyerInfoVO.class, params1, orders1, null);
						//??????????????????
                        List<CHLotVO> xghumanList = rBO.queryXgHumanInfo(xgID);
                        if(buyerList != null && !buyerList.isEmpty() && xghumanList != null && !xghumanList.isEmpty()){
                            if(xghumanList.size() >= buyerList.size()){
                                int j = 0;
                                for(BuyerInfoVO buyerInfo : buyerList){
                                    for(int i = 0; i < xghumanList.size(); i++){
                                        if(buyerInfo.getBuyerName().equals(xghumanList.get(i).getAttribute("name"))
                                                && buyerInfo.getBuyerCardcode().equals(xghumanList.get(i).getAttribute("cardno"))){
                                            j++;
                                        }
                                    }
                                }
                                if(j != buyerList.size()){
									msg = "???????????????????????????????????????????????????";
                                }
                            }else{
								msg = "???????????????????????????????????????????????????";
                            }
                        }else{
							msg = "????????????????????????????????????";
                        }
                    }
                }else{
					msg = "?????????????????????????????????????????????";
                }
            }
            if(!"".equals(msg)){
                map.put("result", "fail");
                map.put("msg", msg);
            }else{
                map.put("result", "success");
                map.put("msg", "");
                map.put("xgID", xgID);
            }
            json = JSONArray.fromObject(map);
            return json;
        }catch (Exception e){
            e.printStackTrace();
            map.put("result", "fail");
			map.put("msg", "??????????????????????????????????????????!");
            json = JSONArray.fromObject(map);
            return json;
        }
    }

    /**
	 * ??????????????????????????????????????????
	 * @param request
	 * @param session
	 * @return
	 */
    @RequestMapping("/saveXgInfo")
    public String saveXgInfo(HttpServletRequest request,HttpSession session) {
        try{
            SignContractBO scBO = new SignContractBO();
            RealestateBO rBO = new RealestateBO();
            XgTempBuyerInfoVO xgBuyerInfoVO = new XgTempBuyerInfoVO();
            XgLimitSaleContractVO xgLimitSaleContractVO = new XgLimitSaleContractVO();
            List<XgBuyerOwnerNameVO> list = new ArrayList<XgBuyerOwnerNameVO>();
            String xgID = request.getParameter("xgID");
            String contractID = request.getParameter("cID");
            List<CHLotVO> xgList = rBO.queryXgInfo(xgID);
            List<CHLotVO> xgHumanList = rBO.queryXgHumanInfo(xgID);

            xgLimitSaleContractVO.setContractID(Long.parseLong(contractID));
            xgLimitSaleContractVO.setLimitSaleID(Long.parseLong(xgID));

            XgTempBuyerInfoVO tempXgBuyerInfoVO = new XgTempBuyerInfoVO();
            tempXgBuyerInfoVO.setLimitSaleID(Long.parseLong(xgID));
            tempXgBuyerInfoVO = (XgTempBuyerInfoVO)scBO.find(tempXgBuyerInfoVO);
			//????????????????????????????????????
            if(tempXgBuyerInfoVO==null){
                if(xgList != null && xgList.size() > 0){
                    CHLotVO chLotVO = (CHLotVO) xgList.get(0);
                    xgBuyerInfoVO.setLimitSaleID(Long.parseLong(xgID));
                    xgBuyerInfoVO.setBuyerName(chLotVO.getAttribute("name") == null ? "" : chLotVO.getAttribute("name").toString());
                    xgBuyerInfoVO.setTel(chLotVO.getAttribute("tel") == null ? "" : chLotVO.getAttribute("tel").toString());
                    xgBuyerInfoVO.setBuyerCardno(chLotVO.getAttribute("cardno") == null ? "" : chLotVO.getAttribute("cardno").toString());
                    xgBuyerInfoVO.setAddress(chLotVO.getAttribute("address") == null ? "" : chLotVO.getAttribute("address").toString());
                    xgBuyerInfoVO.setBuyerDistrictID(Integer.parseInt(chLotVO.getAttribute("housedistrictid").toString()));
                    xgBuyerInfoVO.setUsingState(Integer.parseInt(chLotVO.getState()));
                    xgBuyerInfoVO.setDistrictID(Integer.parseInt(chLotVO.getAttribute("workdistrictid").toString()));
                    xgBuyerInfoVO.setFillDate(DateUtil.parseDateTime(chLotVO.getAttribute("writetime").toString().substring(0, 19)));
                    xgBuyerInfoVO.setConfirmDate(DateUtil.parseDateTime(chLotVO.getAttribute("confirmtime").toString().substring(0, 19)));
                    xgBuyerInfoVO.setPrintDate(DateUtil.parseDateTime(chLotVO.getAttribute("printtime").toString().substring(0, 19)));
                    xgBuyerInfoVO.setEntryDate(DateUtil.parseDateTime(chLotVO.getAttribute("submittime").toString().substring(0, 19)));
                }

                if(xgHumanList != null && xgHumanList.size() > 0){
                    for(CHLotVO cvo : xgHumanList){
                        XgBuyerOwnerNameVO xgBuyerOwnerNameVO = new XgBuyerOwnerNameVO();
                        xgBuyerOwnerNameVO.setLimitSaleID(Long.parseLong(cvo.getAttribute("xgid").toString()));
                        xgBuyerOwnerNameVO.setName(cvo.getAttribute("name") == null ? "" : cvo.getAttribute("name").toString());
                        xgBuyerOwnerNameVO.setCercardTypeID(Long.parseLong(cvo.getAttribute("cardtype").toString()));
                        xgBuyerOwnerNameVO.setCardNo(cvo.getAttribute("cardno") == null ? "" : cvo.getAttribute("cardno").toString());
                        xgBuyerOwnerNameVO.setRightConseqNum(cvo.getAttribute("seqnum") == null ? "" : cvo.getAttribute("seqnum").toString());
                        xgBuyerOwnerNameVO.setMember(Integer.parseInt(cvo.getAttribute("membertype").toString()));
                        list.add(xgBuyerOwnerNameVO);
                    }
                }
            }else{
                xgBuyerInfoVO = null;
                list = null;
            }
            long count = scBO.saveXgInfo(xgLimitSaleContractVO, xgBuyerInfoVO, list);
            if(count <= 0){
				throw new Exception("???????????????");
            }
            String cmd = (String) session.getAttribute("cmd");
            return "redirect:/outer/signcontract/queryEditContractList?cmd="+cmd;
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
    @RequestMapping("/queryEditContract")
    public String queryEditContract(HttpServletRequest request,HttpSession session) {
        try{
            request.setAttribute("cmd", "edit");
            session.setAttribute("cmd", "edit");
            return "/fhhouse/salecontract/signcontract/EditContractQuery";
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
	 * ???????????????????????????
	 * @param request
	 * @param session
	 * @return
	 */
    @RequestMapping("/queryContract")
    public String queryContract(HttpServletRequest request,HttpSession session) {
        try{
            return "/fhhouse/salecontract/signcontract/ContractQuery";
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
    @RequestMapping(value = "/savePresellContract")
    @ResponseBody
    public JSONArray savePresellContract(HttpServletRequest request,HttpSession session,ContractDealVO cdVo,ContractDetailYsVO pcVo,SellerInfoVO siVo) {
        JSONArray json = new JSONArray();
        Map<String , Object> map = new HashMap<String , Object>();
        try{
            HouseVO hvo = null;
            SignContractBO scBO = new SignContractBO();
            //DictionaryBO dicBO = new DictionaryBO();
            SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
            SignerVO signervo = (SignerVO) scBO.find(SignerVO.class, vo.getUserId());
            String contractid = request.getParameter("contractID");
            String houseId = request.getParameter("houseID");
            long i = -1;
            long contractID = Long.parseLong(contractid);
            List<MetaFilter> buyerparams = new ArrayList<MetaFilter>();
            buyerparams.add(new MetaFilter("contractID", "=", contractID));
            List<MetaOrder> buyerorders = new ArrayList<MetaOrder>();
            buyerorders.add(new MetaOrder("serial", MetaOrder.ASC));
            List<BuyerInfoVO> buyerlist = scBO.search(BuyerInfoVO.class, buyerparams, buyerorders, null);
            List<SellerInfoVO> sellerList = scBO.search(SellerInfoVO.class, buyerparams, buyerorders, null);

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
                pcVo.setContractID(contractID);
                pcVo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
                pcVo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
                pcVo.setUpdPerson(vo.getLoginName());

                //ContractDealVO cdVo=(ContractDealVO)scmBo.find(ContractDealVO.class, contractID);
				//??????????????????
                cdVo.setContractID(contractID);
                cdVo.setType(FHConstant.CONTRACT_TYPE_PRESELL);
				cdVo.setSigner(String.valueOf(vo.getUserId())); //???????????????
				cdVo.setBrokercert(signervo.getBrokercert()); //??????????????????
				cdVo.setArea(pcVo.getf0308()); //??????????????????
                if(pcVo.getf0601() != null && "1".equals(pcVo.getf0601())){
					cdVo.setMoney(pcVo.getf0603()); //????????????
                }else if(pcVo.getf0601() != null && "2".equals(pcVo.getf0601())){
					cdVo.setMoney(pcVo.getf0606()); //????????????
                }else if(pcVo.getf0601() != null && "3".equals(pcVo.getf0601())){
					cdVo.setMoney(pcVo.getf0610()); //????????????
                }else if(pcVo.getf0601() != null && "4".equals(pcVo.getf0601())){
					cdVo.setMoney(pcVo.getf0613()); //????????????
                }else if(pcVo.getf0601() != null && "5".equals(pcVo.getf0601())){
					cdVo.setMoney(pcVo.getf0631()); //????????????
                }else if(pcVo.getf0601() != null && "6".equals(pcVo.getf0601())){
					cdVo.setMoney(pcVo.getf0636()); //????????????
                }

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
                List list = scBO.search(BuyerInfoVO.class, params, orders, null);
                if(list.size() == 1){
                    BuyerInfoVO bvo = (BuyerInfoVO) list.get(0);
					cdVo.setBuyerName(bvo.getBuyerName()); //????????????
                }
                if(list.size() > 1){
                    BuyerInfoVO bvo = (BuyerInfoVO) list.get(0);
					cdVo.setBuyerName(bvo.getBuyerName() + "..."); //????????????
                }

				//??????
                cdVo.setLocation(request.getParameter("f0306"));
                /*
				 * cdVo.setRoad(request.getParameter("N2_1")); //??? cdVo.setAlley(request.getParameter("N2_2")); //??? cdVo.setBuildingNumber(request.getParameter("N2_3")); //??????
				 */
                cdVo.setContractversion(FHConstant.NEW_PC_CONTRACTVERSION);
                cdVo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
                cdVo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
                cdVo.setUpdPerson(vo.getLoginName());

                PresellManageContractConfirVO pmccVo = null;
                ContractDealVO earnestVO = null;

				//??????????????????????????????????????????????????????????????????????????????????????????????????????????????????
                String cmd = request.getParameter("cmd");
                if("editSubmit".equals(cmd)){
					cdVo.setStatus(FHConstant.CONTRACT_STATUS_WAIT4SIGN); //??????
					cdVo.setSignDate(DateUtil.getSysDateYYYYMMDD() + DateUtil.getSysDateHHMMSS()); //??????????????????
					cdVo.setOperateDate(DateUtil.getSysDateYYYYMMDD() + DateUtil.getSysDateHHMMSS()); //??????????????????
                }
                if("waitingSubmit".equals(cmd)){
					cdVo.setStatus(FHConstant.CONTRACT_STATUS_SIGNED); //??????
					cdVo.setConfirmer(String.valueOf(vo.getUserId())); //?????????????????????
					cdVo.setConfirmDate(DateUtil.getSysDateYYYYMMDD() + DateUtil.getSysDateHHMMSS()); //??????????????????
					cdVo.setOperateDate(DateUtil.getSysDateYYYYMMDD() + DateUtil.getSysDateHHMMSS()); //??????????????????
                    hvo = (HouseVO) scBO.find(HouseVO.class, cdVo.getHouseID());
					hvo.setStatus(FHConstant.HOUSE_STATUS_SIGNED); //????????????
                    hvo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
                    hvo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
                    hvo.setUpdPerson(vo.getLoginName());
					/* ???????????????????????????????????????????????????????????????????????? */
                    if(pcVo.getEarnestID() != null && !"".equals(pcVo.getEarnestID())){
                        earnestVO = (ContractDealVO) scBO.find(ContractDealVO.class, Long.parseLong(pcVo.getEarnestID()));
						earnestVO.setStatus(FHConstant.CONTRACT_STATUS_RESIGNED); //??????????????????
                        earnestVO.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
                        earnestVO.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
                        earnestVO.setUpdPerson(vo.getLoginName());
                        //scBO.update(earnestVO);
                    }
					/* ?????????????????? */
                    PresellManageEndVO pmeVo = (PresellManageEndVO) scBO.find(PresellManageEndVO.class, hvo.getBuilding_ID());
                    PresellDistrictidManageVO pdmVo = (PresellDistrictidManageVO) scBO.find(PresellDistrictidManageVO.class, hvo.getDistrictID());
                    PresellManageVO pmVo = (PresellManageVO) scBO.find(PresellManageVO.class, hvo.getBuilding_ID());
					//?????????????????????????????????
					if(! (pmeVo != null && pmeVo.getState() == 3)){
                        if(pdmVo != null && pdmVo.getState() == 1){
                            if(pmVo != null && (pmVo.getState() == 1 || pmVo.getState() == 2)){
								//????????????
                                ContractDealVO cdVo2 = (ContractDealVO) scBO.find(ContractDealVO.class, contractID);

                                pmccVo = new PresellManageContractConfirVO();
                                pmccVo.setContractNum(contractID);
                                pmccVo.setOpcode("1001");
                                pmccVo.setHouseNum(Long.parseLong(houseId));
                                String buyerName = "";
                                for(BuyerInfoVO buyerInfoVO : buyerlist){
                                    if(buyerlist.size() == 1){
                                        buyerName = buyerInfoVO.getBuyerName();
                                    }else{
                                        buyerName += buyerInfoVO.getBuyerName() + ",";
                                    }
                                }

                                String buyerCardName = new DictionaryBO().getDictName("CT_506", buyerlist.get(0).getBuyerCardname());
                                pmccVo.setBuyerName(buyerName);
                                pmccVo.setBuyerAcctName(buyerName);
                                pmccVo.setBuyerIDType(buyerCardName);
                                pmccVo.setBuyerIDCode(buyerlist.get(0).getBuyerCardcode());
                                pmccVo.setSellerName(sellerList.get(0).getSellerName());
                                pmccVo.setSellerCopertate(sellerList.get(0).getSellerDelegate());
                                pmccVo.setSellerLicense(sellerList.get(0).getSellerBizcardcode());
                                pmccVo.setAgent(sellerList.get(0).getSellerProxy());
                                pmccVo.setAgentTel(sellerList.get(0).getSellerProxyCall());
                                pmccVo.setManageAcct(pmVo.getManage_Acct());
                                pmccVo.setManageAcctName(pmVo.getManage_Acct_Name());
                                pmccVo.setManageBankName(pmVo.getManage_Bank_Name());
                                pmccVo.setContractamt(Double.parseDouble(cdVo.getMoney()));
                                pmccVo.setHouseArea(Double.parseDouble(cdVo.getArea()));
                                ProjectVO projectVO = (ProjectVO) scBO.find(ProjectVO.class, cdVo.getProjectID());
                                pmccVo.setProjectName(projectVO.getProjectName());
                                pmccVo.setProjectAddress(projectVO.getProjectAdr());
                                pmccVo.setHouseInfo(cdVo.getLocation());
                                pmccVo.setHouseType(hvo.getHouse_Type());
                                pmccVo.setSignDate(cdVo2.getSignDate1().replaceAll("-", ""));
                                pmccVo.setDataKind("1");
                                pmccVo.setProFlag("0");
                                pmccVo.setTotalAmt(Double.parseDouble(cdVo.getMoney()));
                                pmccVo.setDeposit(Double.parseDouble(request.getParameter("f0701")));
                                if(pcVo.getf0707() != null && "2".equals(pcVo.getf0707())){
                                    if(pcVo.getf0707() != null && !"".equals(pcVo.getf0707())){
                                        pmccVo.setDownpay(Double.parseDouble(pcVo.getf0707()));
                                    }
                                    if(pcVo.getf0713() != null && !"".equals(pcVo.getf0713())){
                                        pmccVo.setDownpayDate(pcVo.getf0713().replaceAll("-", ""));
                                    }
                                }
                                if(pcVo.getf0707() != null && "3".equals(pcVo.getf0707())){
                                    if(pcVo.getf0718() != null && !"".equals(pcVo.getf0718())){
                                        pmccVo.setDownpay(Double.parseDouble(pcVo.getf0718()));
                                    }
                                    if(pcVo.getf0717() != null && !"".equals(pcVo.getf0717())){
                                        pmccVo.setDownpayDate(pcVo.getf0717().replaceAll("-", ""));
                                    }
                                    if(pcVo.getf0721() != null && !"".equals(pcVo.getf0721())){
                                        pmccVo.setLoan(Double.parseDouble(pcVo.getf0721()));
                                    }
                                    pmccVo.setLoanFrom("01");
                                }
                                pmccVo.setConfirmTime(DateUtil.parseDateTime2(DateUtil.getSysDateYYYYMMDD() + DateUtil.getSysDateHHMMSS()));
                                //long count = scBO.add(pmccVo);
                                //if(count <= 0){
								//	throw new Exception("??????????????????????????????!");
                                //}
                            }
                        }
                    }

                }


				//????????????
                siVo.setContractID(contractID);
                siVo.setSerial(1);
                siVo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
                siVo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
                siVo.setUpdPerson(vo.getLoginName());

				//????????????
                List<AttachVO> attachList = new ArrayList<AttachVO>();
				//????????????
                int attachSerial[] = { FHConstant.ATTACH_SERIAL_PFJ2, FHConstant.ATTACH_SERIAL_PFJ3, FHConstant.ATTACH_SERIAL_PFJ4,
                        FHConstant.ATTACH_SERIAL_PFJ5, FHConstant.ATTACH_SERIAL_PFJ8, FHConstant.ATTACH_SERIAL_PFJ9, FHConstant.ATTACH_SERIAL_PFJ10,
                        FHConstant.ATTACH_SERIAL_PFJ11 };
                for(int j = 0; j < attachSerial.length; j++){
                    String atcontent = request.getParameter("content" + attachSerial[j]);
                    if(atcontent==null){
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

                i = scBO.saveContract(cdVo, pcVo, null, null, siVo, attachList, hvo, pmccVo, earnestVO);
            }else{
                i = -9999999;
            }

			//			/* ???????????????????????? */
//			HouseBandAverageVO hbaVo = scBO.queryHouseAverage(String.valueOf(cdVo.getHouseID()));
//			ContractDetailYsVO ysVO = (ContractDetailYsVO) scBO.find(ContractDetailYsVO.class, contractID);
//			if(hbaVo != null && ysVO != null && ysVO.getf0602() != null && !"".equals(ysVO.getf0602())){
//				if(Double.parseDouble(ysVO.getf0602()) > hbaVo.getNBandAverage()){
//					i = -111;
//				}else{
//					if(Double.parseDouble(ysVO.getf0602()) < hbaVo.getNBandAverageXia()){
//						i = -222;
//					}
//				}
//			}

            if(i > 0){
                map.put("result", "success");
				map.put("msg", "???????????????????????????");
//			}else if(i == -111){
//				map.put("result", "fail");
				//				map.put("msg", "????????????????????????" + String.valueOf(hbaVo.getNHouseAverage()) + " (???/???), ????????????" + String.valueOf(hbaVo.getNBandAverage()) + "(???/???)???\n???????????????????????????????????????????????????");
//			}else if(i == -222){
//				map.put("result", "fail");
				//				map.put("msg", "?????????????????????????????????????????????????????????????????????????????????");
            }else if(i == -9999999){
                map.put("result", "fail");
				map.put("msg", "?????????????????????????????????");
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
	 * @param session
	 * @return
	 */
    @RequestMapping(value = "/updatePresellContract")
    @ResponseBody
    public JSONArray updatePresellContract(HttpServletRequest request,HttpSession session,ContractDealVO cdVo,ContractDetailYsVO pcVo,SellerInfoVO siVo) {
    	JSONArray json = new JSONArray();
    	Map<String , Object> map = new HashMap<String , Object>();
    	try{
    		HouseVO hvo = null;
    		SignContractBO scBO = new SignContractBO();
    		//DictionaryBO dicBO = new DictionaryBO();
    		SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
    		String contractid = request.getParameter("contractID");
    		String houseId = request.getParameter("houseID");
    		long i = -1;
    		long contractID = Long.parseLong(contractid);
    		List<MetaFilter> buyerparams = new ArrayList<MetaFilter>();
    		buyerparams.add(new MetaFilter("contractID", "=", contractID));
    		List<MetaOrder> buyerorders = new ArrayList<MetaOrder>();
    		buyerorders.add(new MetaOrder("serial", MetaOrder.ASC));
    		List<BuyerInfoVO> buyerlist = scBO.search(BuyerInfoVO.class, buyerparams, buyerorders, null);
    		List<SellerInfoVO> sellerList = scBO.search(SellerInfoVO.class, buyerparams, buyerorders, null);
    		
    		if(buyerlist != null && buyerlist.size() > 0){
				//??????????????????
    			ContractDetailYsVO ysVo = new ContractDetailYsVO();
    			ysVo.setContractID(contractID);
    			ysVo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
    			ysVo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
    			ysVo.setUpdPerson(vo.getLoginName());
    			//ContractDealVO cdVo=(ContractDealVO)scmBo.find(ContractDealVO.class, contractID);
				//??????????????????
    			ContractDealVO dealVo = new ContractDealVO();
    			dealVo.setContractID(contractID);
    			dealVo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
    			dealVo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
    			dealVo.setUpdPerson(vo.getLoginName());
    			PresellManageContractConfirVO pmccVo = null;
    			ContractDealVO earnestVO = null;
    			
				//??????????????????????????????????????????????????????????????????????????????????????????????????????????????????
    			String cmd = request.getParameter("cmd");
    			if("waitingSubmit".equals(cmd)){
					dealVo.setStatus(FHConstant.CONTRACT_STATUS_SIGNED); //??????
					dealVo.setConfirmer(String.valueOf(vo.getUserId())); //?????????????????????
					dealVo.setConfirmDate(DateUtil.getSysDateYYYYMMDD() + DateUtil.getSysDateHHMMSS()); //??????????????????
					dealVo.setOperateDate(DateUtil.getSysDateYYYYMMDD() + DateUtil.getSysDateHHMMSS()); //??????????????????
    				hvo = (HouseVO) scBO.find(HouseVO.class, cdVo.getHouseID());
					hvo.setStatus(FHConstant.HOUSE_STATUS_SIGNED); //????????????
    				hvo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
    				hvo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
    				hvo.setUpdPerson(vo.getLoginName());
					/* ???????????????????????????????????????????????????????????????????????? */
    				if(pcVo.getEarnestID() != null && !"".equals(pcVo.getEarnestID())){
    					earnestVO = (ContractDealVO) scBO.find(ContractDealVO.class, Long.parseLong(pcVo.getEarnestID()));
						earnestVO.setStatus(FHConstant.CONTRACT_STATUS_RESIGNED); //??????????????????
    					earnestVO.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
    					earnestVO.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
    					earnestVO.setUpdPerson(vo.getLoginName());
    					//scBO.update(earnestVO);
    				}
					/* ?????????????????? */
    				PresellManageEndVO pmeVo = (PresellManageEndVO) scBO.find(PresellManageEndVO.class, hvo.getBuilding_ID());
    				PresellDistrictidManageVO pdmVo = (PresellDistrictidManageVO) scBO.find(PresellDistrictidManageVO.class, hvo.getDistrictID());
    				PresellManageVO pmVo = (PresellManageVO) scBO.find(PresellManageVO.class, hvo.getBuilding_ID());
					//?????????????????????????????????
					if(! (pmeVo != null && pmeVo.getState() == 3)){
    					if(pdmVo != null && pdmVo.getState() == 1){
    						if(pmVo != null && (pmVo.getState() == 1 || pmVo.getState() == 2)){
								//????????????
    							ContractDealVO cdVo2 = (ContractDealVO) scBO.find(ContractDealVO.class, contractID);
    							
    							pmccVo = new PresellManageContractConfirVO();
    							pmccVo.setContractNum(contractID);
    							pmccVo.setOpcode("1001");
    							pmccVo.setHouseNum(Long.parseLong(houseId));
    							String buyerName = "";
    							for(BuyerInfoVO buyerInfoVO : buyerlist){
    								if(buyerlist.size() == 1){
    									buyerName = buyerInfoVO.getBuyerName();
    								}else{
    									buyerName += buyerInfoVO.getBuyerName() + ",";
    								}
    							}
    							
    							String buyerCardName = new DictionaryBO().getDictName("CT_506", buyerlist.get(0).getBuyerCardname());
    							pmccVo.setBuyerName(buyerName);
    							pmccVo.setBuyerAcctName(buyerName);
    							pmccVo.setBuyerIDType(buyerCardName);
    							pmccVo.setBuyerIDCode(buyerlist.get(0).getBuyerCardcode());
    							pmccVo.setSellerName(sellerList.get(0).getSellerName());
    							pmccVo.setSellerCopertate(sellerList.get(0).getSellerDelegate());
    							pmccVo.setSellerLicense(sellerList.get(0).getSellerBizcardcode());
    							pmccVo.setAgent(sellerList.get(0).getSellerProxy());
    							pmccVo.setAgentTel(sellerList.get(0).getSellerProxyCall());
    							pmccVo.setManageAcct(pmVo.getManage_Acct());
    							pmccVo.setManageAcctName(pmVo.getManage_Acct_Name());
    							pmccVo.setManageBankName(pmVo.getManage_Bank_Name());
    							pmccVo.setContractamt(Double.parseDouble(cdVo2.getMoney()));
    							pmccVo.setHouseArea(Double.parseDouble(cdVo2.getArea()));
    							ProjectVO projectVO = (ProjectVO) scBO.find(ProjectVO.class, cdVo2.getProjectID());
    							pmccVo.setProjectName(projectVO.getProjectName());
    							pmccVo.setProjectAddress(projectVO.getProjectAdr());
    							pmccVo.setHouseInfo(cdVo2.getLocation());
    							pmccVo.setHouseType(hvo.getHouse_Type());
    							pmccVo.setSignDate(cdVo2.getSignDate1().replaceAll("-", ""));
    							pmccVo.setDataKind("1");
    							pmccVo.setProFlag("0");
    							pmccVo.setTotalAmt(Double.parseDouble(cdVo2.getMoney()));
    							pmccVo.setDeposit(Double.parseDouble(request.getParameter("f0701")));
    							if(pcVo.getf0707() != null && "2".equals(pcVo.getf0707())){
    								if(pcVo.getf0707() != null && !"".equals(pcVo.getf0707())){
    									pmccVo.setDownpay(Double.parseDouble(pcVo.getf0707()));
    								}
    								if(pcVo.getf0713() != null && !"".equals(pcVo.getf0713())){
    									pmccVo.setDownpayDate(pcVo.getf0713().replaceAll("-", ""));
    								}
    							}
    							if(pcVo.getf0707() != null && "3".equals(pcVo.getf0707())){
    								if(pcVo.getf0718() != null && !"".equals(pcVo.getf0718())){
    									pmccVo.setDownpay(Double.parseDouble(pcVo.getf0718()));
    								}
    								if(pcVo.getf0717() != null && !"".equals(pcVo.getf0717())){
    									pmccVo.setDownpayDate(pcVo.getf0717().replaceAll("-", ""));
    								}
    								if(pcVo.getf0721() != null && !"".equals(pcVo.getf0721())){
    									pmccVo.setLoan(Double.parseDouble(pcVo.getf0721()));
    								}
    								pmccVo.setLoanFrom("01");
    							}
    							pmccVo.setConfirmTime(DateUtil.parseDateTime2(DateUtil.getSysDateYYYYMMDD() + DateUtil.getSysDateHHMMSS()));
    						}
    					}
    				}
    				
    			}
    			
				//????????????
                siVo.setContractID(contractID);
                siVo.setSerial(1);
                siVo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
                siVo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
                siVo.setUpdPerson(vo.getLoginName());
    			
    			i = scBO.saveContract(dealVo, pcVo, null, null, null, null, hvo, pmccVo, earnestVO);
    		}else{
    			i = -9999999;
    		}
    		
    		if(i > 0){
    			map.put("result", "success");
				map.put("msg", "???????????????????????????");
    		}else if(i == -9999999){
    			map.put("result", "fail");
				map.put("msg", "?????????????????????????????????");
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
	 * @param session
	 * @return
	 */
    @RequestMapping(value = "/saveSellContract")
    @ResponseBody
    public JSONArray saveSellContract(HttpServletRequest request,HttpSession session,ContractDealVO cdVo,ContractDetailCsVO scVo,SellerInfoVO siVo) {
        JSONArray json = new JSONArray();
        Map<String , Object> map = new HashMap<String , Object>();
        try{
            HouseVO hvo = null;
            SignContractBO scBO = new SignContractBO();
            //DictionaryBO dicBO = new DictionaryBO();
            SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
            SignerVO signervo = (SignerVO) scBO.find(SignerVO.class, vo.getUserId());
            String contractid = request.getParameter("contractID");
            long i = -1;
            long contractID = Long.parseLong(contractid);
            List<MetaFilter> buyerparams = new ArrayList<MetaFilter>();
            buyerparams.add(new MetaFilter("contractID", "=", contractID));
            List<BuyerInfoVO> buyerlist = scBO.search(BuyerInfoVO.class, buyerparams);

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
                cdVo.setType(FHConstant.CONTRACT_TYPE_SELL);
				cdVo.setSigner(String.valueOf(vo.getUserId())); //???????????????
				cdVo.setBrokercert(signervo.getBrokercert()); //??????????????????
				cdVo.setArea(scVo.getf0308()); //??????????????????
                if(scVo.getf0701() != null && "1".equals(scVo.getf0701())){
					cdVo.setMoney(scVo.getf0703()); //????????????
                }else if(scVo.getf0701() != null && "2".equals(scVo.getf0701())){
					cdVo.setMoney(scVo.getf0706()); //????????????
                }else if(scVo.getf0701() != null && "3".equals(scVo.getf0701())){
					cdVo.setMoney(scVo.getf0710()); //????????????
                }else if(scVo.getf0701() != null && "4".equals(scVo.getf0701())){
					cdVo.setMoney(scVo.getf0713()); //????????????
                }else if(scVo.getf0701() != null && "5".equals(scVo.getf0701())){
					cdVo.setMoney(scVo.getf0731()); //????????????
                }else if(scVo.getf0701() != null && "6".equals(scVo.getf0701())){
					cdVo.setMoney(scVo.getf0736()); //????????????
                }

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
                List list = scBO.search(BuyerInfoVO.class, params, orders, null);
                if(list.size() == 1){
                    BuyerInfoVO bvo = (BuyerInfoVO) list.get(0);
					cdVo.setBuyerName(bvo.getBuyerName()); //????????????
                }
                if(list.size() > 1){
                    BuyerInfoVO bvo = (BuyerInfoVO) list.get(0);
					cdVo.setBuyerName(bvo.getBuyerName() + "..."); //????????????
                }

				//cdvo.setLandType();	//?????????????????????   0:?????????1:?????????2:?????????3:??????
				//??????
                cdVo.setLocation(request.getParameter("f0306"));
                /*
				 * cdVo.setRoad(request.getParameter("N2_1")); //??? cdVo.setAlley(request.getParameter("N2_2")); //??? cdVo.setBuildingNumber(request.getParameter("N2_3")); //??????
				 */
                cdVo.setContractversion(FHConstant.NEW_SC_CONTRACTVERSION);
                cdVo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
                cdVo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
                cdVo.setUpdPerson(vo.getLoginName());

                ContractDealVO earnestVO = null;
				//??????????????????????????????????????????????????????????????????????????????????????????????????????????????????
                String cmd = request.getParameter("cmd");
                if("editSubmit".equals(cmd)){
					cdVo.setStatus(FHConstant.CONTRACT_STATUS_WAIT4SIGN); //??????
					cdVo.setSignDate(DateUtil.getSysDateYYYYMMDD() + DateUtil.getSysDateHHMMSS()); //??????????????????
					cdVo.setOperateDate(DateUtil.getSysDateYYYYMMDD() + DateUtil.getSysDateHHMMSS()); //??????????????????
                }
                if("waitingSubmit".equals(cmd)){
					cdVo.setStatus(FHConstant.CONTRACT_STATUS_SIGNED); //??????
					cdVo.setConfirmer(String.valueOf(vo.getUserId())); //?????????????????????
					cdVo.setConfirmDate(DateUtil.getSysDateYYYYMMDD() + DateUtil.getSysDateHHMMSS()); //??????????????????
					cdVo.setOperateDate(DateUtil.getSysDateYYYYMMDD() + DateUtil.getSysDateHHMMSS()); //??????????????????
                    hvo = (HouseVO) scBO.find(HouseVO.class, cdVo.getHouseID());
					hvo.setStatus(FHConstant.HOUSE_STATUS_SIGNED); //????????????
                    hvo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
                    hvo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
                    hvo.setUpdPerson(vo.getLoginName());
					/* ???????????????????????????????????????????????????????????????????????? */
                    if(scVo.getEarnestID() != null && !"".equals(scVo.getEarnestID())){
                        earnestVO = (ContractDealVO) scBO.find(ContractDealVO.class, Long.parseLong(scVo.getEarnestID()));
						earnestVO.setStatus(FHConstant.CONTRACT_STATUS_RESIGNED); //??????????????????
                        earnestVO.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
                        earnestVO.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
                        earnestVO.setUpdPerson(vo.getLoginName());
                        //scBO.update(earnestVO);
                    }
                }


				//????????????
                siVo.setContractID(contractID);
                siVo.setSerial(1);
                siVo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
                siVo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
                siVo.setUpdPerson(vo.getLoginName());

                List<AttachVO> attachList = new ArrayList<AttachVO>();
				//????????????
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

                i = scBO.saveContract(cdVo, null, scVo, null, siVo, attachList, hvo, null, earnestVO);
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
	 * @param session
	 * @return
	 */
    @RequestMapping(value = "/updateSellContract")
    @ResponseBody
    public JSONArray updateSellContract(HttpServletRequest request,HttpSession session,ContractDealVO cdVo,ContractDetailCsVO scVo,SellerInfoVO siVo) {
        JSONArray json = new JSONArray();
        Map<String , Object> map = new HashMap<String , Object>();
        try{
            HouseVO hvo = null;
            SignContractBO scBO = new SignContractBO();
            //DictionaryBO dicBO = new DictionaryBO();
            SmUserVO vo = (SmUserVO) session.getAttribute("LgUser");
            String contractid = request.getParameter("contractID");
            long i = -1;
            long contractID = Long.parseLong(contractid);
            List<MetaFilter> buyerparams = new ArrayList<MetaFilter>();
            buyerparams.add(new MetaFilter("contractID", "=", contractID));
            List<BuyerInfoVO> buyerlist = scBO.search(BuyerInfoVO.class, buyerparams);

            if(buyerlist != null && buyerlist.size() > 0){
				//??????????????????
            	ContractDetailCsVO csVo = new ContractDetailCsVO();
            	csVo.setContractID(contractID);
            	csVo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
            	csVo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
            	csVo.setUpdPerson(vo.getLoginName());

                //ContractDealVO cdVo=(ContractDealVO)scmBo.find(ContractDealVO.class, contractID);
				//??????????????????
            	ContractDealVO dealVO = new ContractDealVO();
            	dealVO.setContractID(contractID);
            	dealVO.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
            	dealVO.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
            	dealVO.setUpdPerson(vo.getLoginName());

                ContractDealVO earnestVO = null;
				//??????????????????????????????????????????????????????????????????????????????????????????????????????????????????
                String cmd = request.getParameter("cmd");
                if("waitingSubmit".equals(cmd)){
					dealVO.setStatus(FHConstant.CONTRACT_STATUS_SIGNED); //??????
					dealVO.setConfirmer(String.valueOf(vo.getUserId())); //?????????????????????
					dealVO.setConfirmDate(DateUtil.getSysDateYYYYMMDD() + DateUtil.getSysDateHHMMSS()); //??????????????????
					dealVO.setOperateDate(DateUtil.getSysDateYYYYMMDD() + DateUtil.getSysDateHHMMSS()); //??????????????????
                    hvo = (HouseVO) scBO.find(HouseVO.class, cdVo.getHouseID());
					hvo.setStatus(FHConstant.HOUSE_STATUS_SIGNED); //????????????
                    hvo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
                    hvo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
                    hvo.setUpdPerson(vo.getLoginName());
					/* ???????????????????????????????????????????????????????????????????????? */
                    if(scVo.getEarnestID() != null && !"".equals(scVo.getEarnestID())){
                        earnestVO = (ContractDealVO) scBO.find(ContractDealVO.class, Long.parseLong(scVo.getEarnestID()));
						earnestVO.setStatus(FHConstant.CONTRACT_STATUS_RESIGNED); //??????????????????
                        earnestVO.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
                        earnestVO.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
                        earnestVO.setUpdPerson(vo.getLoginName());
                        //scBO.update(earnestVO);
                    }
                }


				//????????????
                siVo.setContractID(contractID);
                siVo.setSerial(1);
                siVo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
                siVo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
                siVo.setUpdPerson(vo.getLoginName());

                i = scBO.saveContract(dealVO, null, csVo, null, siVo, null, hvo, null, earnestVO);
            }else{
                i = -9999999;
            }

            if(i > 0){
                map.put("result", "success");
				map.put("msg", "???????????????????????????");
            }else if(i == -9999999){
                map.put("result", "fail");
				map.put("msg", "?????????????????????????????????");
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
	 * @param session
	 * @return
	 */
    @RequestMapping("/saveEarnestContract")
    @ResponseBody
    public JSONArray saveEarnestContract(HttpServletRequest request,HttpSession session,ContractDealVO cdvo,EarnestContractVO ecvo,SellerInfoVO svo) {
        JSONArray json = new JSONArray();
        Map<String , Object> map = new HashMap<String , Object>();
        try{

            HouseVO hvo = null;
            SignContractBO scBO = new SignContractBO();
            SmUserVO uservo = (SmUserVO) session.getAttribute("LgUser");
            SignerVO signervo = (SignerVO) scBO.find(SignerVO.class, uservo.getUserId());
            EnterpriseQualifyVO eqvo = (EnterpriseQualifyVO) scBO.find(EnterpriseQualifyVO.class, Long.parseLong(signervo.getComp_ID()));

            long contractId = Long.parseLong(request.getParameter("contractID"));
			/* ?????????????????? */
			ecvo.setContractID(contractId); //????????????
            ecvo.setN1_12(request.getParameter("N1_12").equals("") ? "" : request.getParameter("N1_12").replaceAll("-", "").substring(0, 4));
            ecvo.setN1_13(request.getParameter("N1_12").equals("") ? "" : request.getParameter("N1_12").replaceAll("-", "").substring(4, 6));
            ecvo.setN1_14(request.getParameter("N1_12").equals("") ? "" : request.getParameter("N1_12").replaceAll("-", "").substring(6, 8));
            ecvo.setN4_2(ecvo.getN4_2().equals("") ? "" : ecvo.getN4_2().replaceAll("-", ""));
            //ecvo.setCreDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
            //ecvo.setCreTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
            //ecvo.setCrePerson(uservo.getLoginName());
            ecvo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
            ecvo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
            ecvo.setUpdPerson(uservo.getLoginName());

			/* ?????????????????? */
			cdvo.setContractID(contractId); //????????????
			cdvo.setSigner(String.valueOf(uservo.getUserId())); //???????????????
			cdvo.setBrokercert(signervo.getBrokercert()); //??????????????????

			cdvo.setArea(request.getParameter("N1_11")); //??????????????????
			cdvo.setMoney(request.getParameter("N2_3")); //????????????
			cdvo.setSellerName(request.getParameter("sellerName")); //????????????
            List<MetaFilter> params = new ArrayList<MetaFilter>();
            params.add(new MetaFilter("contractID", "=", contractId));
            List<MetaOrder> orders = new ArrayList<MetaOrder>();
            orders.add(new MetaOrder("serial", MetaOrder.ASC));
            List list = scBO.search(BuyerInfoVO.class, params, orders, null);
            if(list.size() == 1){
                BuyerInfoVO bvo = (BuyerInfoVO) list.get(0);
				cdvo.setBuyerName(bvo.getBuyerName()); //????????????
            }
            if(list.size() > 1){
                BuyerInfoVO bvo = (BuyerInfoVO) list.get(0);
				cdvo.setBuyerName(bvo.getBuyerName() + "..."); //????????????
            }
			/* ????????????????????? */
			//cdvo.setConfirmer(uservo.getUserId());	//?????????????????????
			//cdvo.setConfirmDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()+DateUtil.getSysDateHHMMSS()));  //??????????????????
			//??????
			cdvo.setLocation(request.getParameter("N1_1") + request.getParameter("N1_2") + "???" + request.getParameter("projectName") + "???"
                    + request.getParameter("N1_4") + request.getParameter("N1_6") + request.getParameter("N1_7"));
			cdvo.setRoad(request.getParameter("N1_1")); //???
			cdvo.setAlley(request.getParameter("N1_2")); //???
			cdvo.setBuildingNumber(request.getParameter("N1_4")); //??????
            cdvo.setContractversion(FHConstant.EC_CONTRACTVERSION);
            cdvo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
            cdvo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
            cdvo.setUpdPerson(uservo.getLoginName());
            //cdvo.setCreDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
            //cdvo.setCreTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
            //cdvo.setCrePerson(uservo.getLoginName());
			cdvo.setWaitday(request.getParameter("N4_1")); //????????????
			if("editSubmit".equals(request.getParameter("cmd"))){ //????????????????????????????????????????????????
				cdvo.setStatus(FHConstant.CONTRACT_STATUS_WAIT4SIGN); //??????
				cdvo.setSignDate(DateUtil.getSysDateYYYYMMDD() + DateUtil.getSysDateHHMMSS()); //??????????????????
				cdvo.setOperateDate(DateUtil.getSysDateYYYYMMDD() + DateUtil.getSysDateHHMMSS()); //??????????????????
            }
            if("waitingSubmit".equals(request.getParameter("cmd"))){
				cdvo.setStatus(FHConstant.CONTRACT_STATUS_SIGNED); //??????
				cdvo.setConfirmer(String.valueOf(uservo.getUserId())); //?????????????????????
				cdvo.setConfirmDate(DateUtil.getSysDateYYYYMMDD() + DateUtil.getSysDateHHMMSS()); //??????????????????
				cdvo.setOperateDate(DateUtil.getSysDateYYYYMMDD() + DateUtil.getSysDateHHMMSS()); //??????????????????
                hvo = (HouseVO) scBO.find(HouseVO.class, cdvo.getHouseID());
				hvo.setStatus(FHConstant.HOUSE_STATUS_EARNEST); //??????????????????
                hvo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
                hvo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
                hvo.setUpdPerson(uservo.getLoginName());
            }

			/* ?????????????????? */
            svo.setContractID(contractId);
            svo.setCompID(eqvo.getComp_ID());
            svo.setSerial(1);
            svo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
            svo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
            svo.setUpdPerson(uservo.getLoginName());
            //svo.setCreDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
            //svo.setCreTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
            //svo.setCrePerson(uservo.getLoginName());
            boolean result = scBO.saveEarnestContract(cdvo, ecvo, svo, hvo);
            if(list == null || list.size() <= 0){
                map.put("result", "fail");
				map.put("message", "?????????????????????????????????");
            }else if(result == false){
                map.put("result", "fail");
				map.put("message", "???????????????????????????");
            }else{
                map.put("result", "success");
				map.put("message", "???????????????????????????");
            }
            json = JSONArray.fromObject(map);
            return json;
        }catch (Exception e){
            e.printStackTrace();
            map.put("result", "fail");
			map.put("message", "???????????????????????????");
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
    @RequestMapping("/updateEarnestContract")
    @ResponseBody
    public JSONArray updateEarnestContract(HttpServletRequest request,HttpSession session,ContractDealVO cdvo,EarnestContractVO ecvo,SellerInfoVO svo) {
        JSONArray json = new JSONArray();
        Map<String , Object> map = new HashMap<String , Object>();
        try{

            HouseVO hvo = null;
            SignContractBO scBO = new SignContractBO();
            SmUserVO uservo = (SmUserVO) session.getAttribute("LgUser");
            SignerVO signervo = (SignerVO) scBO.find(SignerVO.class, uservo.getUserId());
            EnterpriseQualifyVO eqvo = (EnterpriseQualifyVO) scBO.find(EnterpriseQualifyVO.class, Long.parseLong(signervo.getComp_ID()));

            long contractId = Long.parseLong(request.getParameter("contractID"));
			/* ?????????????????? */
            EarnestContractVO earnestContractVO = new EarnestContractVO();
			earnestContractVO.setContractID(contractId); //????????????
            earnestContractVO.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
            earnestContractVO.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
            earnestContractVO.setUpdPerson(uservo.getLoginName());
            List<MetaFilter> params = new ArrayList<MetaFilter>();
            params.add(new MetaFilter("contractID", "=", contractId));
            List<MetaOrder> orders = new ArrayList<MetaOrder>();
            orders.add(new MetaOrder("serial", MetaOrder.ASC));
            List list = scBO.search(BuyerInfoVO.class, params, orders, null);
            if(list.size() == 1){
                BuyerInfoVO bvo = (BuyerInfoVO) list.get(0);
				cdvo.setBuyerName(bvo.getBuyerName()); //????????????
            }
            if(list.size() > 1){
                BuyerInfoVO bvo = (BuyerInfoVO) list.get(0);
				cdvo.setBuyerName(bvo.getBuyerName() + "..."); //????????????
            }
			/* ?????????????????? */
            ContractDealVO dealVo = new ContractDealVO();
			dealVo.setContractID(contractId); //????????????
            dealVo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
            dealVo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
            dealVo.setUpdPerson(uservo.getLoginName());
            if("waitingSubmit".equals(request.getParameter("cmd"))){
				dealVo.setStatus(FHConstant.CONTRACT_STATUS_SIGNED); //??????
				dealVo.setConfirmer(String.valueOf(uservo.getUserId())); //?????????????????????
				dealVo.setConfirmDate(DateUtil.getSysDateYYYYMMDD() + DateUtil.getSysDateHHMMSS()); //??????????????????
				dealVo.setOperateDate(DateUtil.getSysDateYYYYMMDD() + DateUtil.getSysDateHHMMSS()); //??????????????????
                hvo = (HouseVO) scBO.find(HouseVO.class, cdvo.getHouseID());
				hvo.setStatus(FHConstant.HOUSE_STATUS_EARNEST); //??????????????????
                hvo.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
                hvo.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
                hvo.setUpdPerson(uservo.getLoginName());
            }

			/* ?????????????????? */
            SellerInfoVO siVO = new SellerInfoVO();
            siVO.setContractID(contractId);
            siVO.setUpdDate(Long.parseLong(DateUtil.getSysDateYYYYMMDD()));
            siVO.setUpdTime(Long.parseLong(DateUtil.getSysDateHHMMSS()));
            siVO.setUpdPerson(uservo.getLoginName());
            boolean result = scBO.saveEarnestContract(dealVo, earnestContractVO, siVO, hvo);
            if(list == null || list.size() <= 0){
                map.put("result", "fail");
				map.put("message", "?????????????????????????????????");
            }else if(result == false){
                map.put("result", "fail");
				map.put("message", "???????????????????????????");
            }else{
                map.put("result", "success");
				map.put("message", "???????????????????????????");
            }
            json = JSONArray.fromObject(map);
            return json;
        }catch (Exception e){
            e.printStackTrace();
            map.put("result", "fail");
			map.put("message", "???????????????????????????");
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
    @RequestMapping("/contractSellerCheck")
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
            return "/fhhouse/salecontract/signcontract/ContractSellerCheck";
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


    @RequestMapping("/signXGCheck")
    @ResponseBody
    public JSON signXGCheck(HttpServletRequest request,HttpSession session) {
        Map<String , Object> map = new HashMap<String , Object>();
        String msg = null;
        try{
            String xgID = request.getParameter("xgID");
            String contractID = request.getParameter("contractID");
            String houseId = request.getParameter("houseID");

            SignContractBO sBo = new SignContractBO();
            List<MetaFilter> params = new ArrayList<MetaFilter>();
            params.add(new MetaFilter("contractID", "=", contractID));
            List<MetaOrder> orders = new ArrayList<MetaOrder>();
            orders.add(new MetaOrder("serial", MetaOrder.ASC));
            List<BuyerInfoVO> buyerList = sBo.search(BuyerInfoVO.class, params, orders, null);
            if(buyerList != null && !buyerList.isEmpty()){
				/*** 20181120 added by gcf ?????????????????????????????????????????????????????????????????????????????????-start ****/
            	//TODO
            	//if(1!=1){
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
                //}
            	}
				/*** 20181120 added by gcf ?????????????????????????????????????????????????????????????????????????????????-end ****/

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
                
                if(xgCBuyerList!=null && xgCBuyerList.size()>0){
	                boolean xgFlag = true;
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
    @RequestMapping(value = "/doQueryCheckOther")
    @ResponseBody
    public JSONArray doQueryCheckOther(HttpServletRequest request,HttpSession session) {
        JSONArray json = new JSONArray();
        Map<String , Object> map = new HashMap<String , Object>();
        SignContractBO scBO = new SignContractBO();
        try{
            String houseID = request.getParameter("houseID");
            List<HouseVO> list = scBO.checkOtherRightByHouseID(houseID);
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
