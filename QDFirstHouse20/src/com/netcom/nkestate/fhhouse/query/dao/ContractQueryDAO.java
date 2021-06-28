package com.netcom.nkestate.fhhouse.query.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.netcom.nkestate.common.MiniDAO;
import com.netcom.nkestate.fhhouse.salecontract.vo.BuyerInfoVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.BuyerInfoVOFHE;
import com.netcom.nkestate.fhhouse.salecontract.vo.ContractDealVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.DeliverContractVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.SellTemplateVO;
import com.netcom.nkestate.fhhouse.salecontract.vo.SellerInfoVO;
import com.netcom.nkestate.framework.Page;
import com.netcom.nkestate.framework.dao.DataBaseUtil;

/**
 * 合同管理-合同查询DAO
 */
public class ContractQueryDAO extends MiniDAO {

	static Logger logger = Logger.getLogger(ContractQueryDAO.class.getName());


	/**
	 * 功能描述：合同查询（乙方）列表
	 * @param buyerName
	 * @param buyerCardcode
	 * @param buyerCardCodeN15
	 * @param buyerBirth
	 * @param buyerNationality
	 * @param buyerProxy
	 * @param buyerCardname
	 * @param buyerType
	 * @param buyerProvince
	 * @param buyerAddress
	 * @param districtStr
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<ContractDealVO> queryContractDealFHE(String buyerName,String buyerCardcode,String buyerCardCodeN15,String buyerBirth,String buyerNationality,String buyerProxy,String buyerCardname,String buyerType,String buyerProvince,String buyerAddress,String districtStr,Page page)
			throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select distinct contractdeal.contract_id,contractdeal.type,contractdeal.signdate,to_char(contractdeal.status) as status,contractdeal.signer,signer.loginname,contractdeal.seller_name,contractdeal.buyer_name,contractdeal.location,buyerinfo.serial as serial ");
			sb.append(" from contractdeal, signer,buyerinfo,house ");
			sb.append(" where  contractdeal.contract_id = buyerinfo.contract_id  ");
			sb.append("  and contractdeal.signer = signer.signer_id  ");
			sb.append("  and contractdeal.status in (2, 4, 5, 7, 10, 11)  ");
			sb.append("  and house.house_id=contractdeal.house_id ");
			sb.append(" and house.districtid in " + districtStr);

			if(buyerProvince != null && !"".equals(buyerProvince) && !"-999".equals(buyerProvince)){
				sb.append("   and buyerinfo.buyer_province = ?  ");
				params.add(buyerProvince);
			}
			if(buyerNationality != null && !"".equals(buyerNationality) && !"-999".equals(buyerNationality)){
				sb.append("   and buyerinfo.buyer_nationality = ?  ");
				params.add(buyerNationality);
			}
			if(buyerBirth != null && !"".equals(buyerBirth)){
				sb.append("   and buyerinfo.buyer_birth = ? ");
				params.add(buyerBirth.replaceAll("-", ""));
			}
			//当输入姓名大于5个汉字时,进行姓名、证件号的模糊查询
			if(buyerName.getBytes("UTF-8").length > 15){
				if(buyerCardcode != null && !"".equals(buyerCardcode)){
					sb.append("   and buyerinfo.buyer_cardcode like ?  ");
					params.add("%" + buyerCardcode + "%");
					sb.append("   or buyerinfo.buyer_cardcode like ?  ");
					params.add("%" + buyerCardCodeN15 + "%");
				}
				sb.append("   and buyerinfo.buyer_name like ?  ");
				params.add("%" + buyerName + "%");
			}else{
				if(buyerCardcode != null && !"".equals(buyerCardcode)){
					sb.append("   and buyerinfo.buyer_cardcode in ('" + buyerCardcode + "','" + buyerCardCodeN15 + "')  ");
				}
				if(buyerName != null && !"".equals(buyerName)){
					sb.append("   and buyerinfo.buyer_name = ?  ");
					params.add(buyerName);
				}
			}
			if(buyerAddress != null && !"".equals(buyerAddress)){
				sb.append("   and buyerinfo.buyer_address like ?  ");
				params.add(buyerAddress);
			}
			if(buyerProxy != null && !"".equals(buyerProxy)){
				sb.append("   and buyerinfo.buyer_proxy = ?  ");
				params.add(buyerProxy);
			}
			if(buyerCardname != null && !"".equals(buyerCardname) && !"-999".equals(buyerCardname)){
				sb.append("   and buyerinfo.buyer_cardname = ?  ");
				params.add(buyerCardname);
			}
			if(buyerType != null && !"".equals(buyerType) && !"-999".equals(buyerType)){
				sb.append("   and buyerinfo.buyer_type = ?  ");
				params.add(buyerType);
			}

			sb.append("  union all ");
			sb.append(" select distinct contractdeal.contract_id,contractdeal.type,contractdeal.signdate,'8' as status,contractdeal.signer,signer.loginname,contractdeal.seller_name,contractdeal.buyer_name,contractdeal.location,buyerinfo.serial as serial ");
			sb.append(" from contractdeal,contractdeal contractdeal2, signer,buyerinfo,sellcontract,earnestcontract,house ");
			sb.append(" where contractdeal.contract_id = buyerinfo.contract_id  ");
			sb.append("   and contractdeal.signer = signer.signer_id  ");
			sb.append("   and contractdeal.contract_id = sellcontract.contract_id  ");
			sb.append("   and sellcontract.earnest_id = earnestcontract.contract_id  ");
			sb.append("   and contractdeal2.contract_id = earnestcontract.contract_id  ");
			sb.append("   and contractdeal2.status = 2  ");
			sb.append("   and contractdeal.status in(0 , 1)  ");
			sb.append("   and house.house_id=contractdeal.house_id  ");
			sb.append(" and house.districtid in " + districtStr);
			if(buyerProvince != null && !"".equals(buyerProvince) && !"-999".equals(buyerProvince)){
				sb.append("   and buyerinfo.buyer_province = ?  ");
				params.add(buyerProvince);
			}
			if(buyerNationality != null && !"".equals(buyerNationality) && !"-999".equals(buyerNationality)){
				sb.append("   and buyerinfo.buyer_nationality = ?  ");
				params.add(buyerNationality);
			}
			if(buyerBirth != null && !"".equals(buyerBirth)){
				sb.append("   and buyerinfo.buyer_birth = ? ");
				params.add(buyerBirth.replaceAll("-", ""));
			}
			//当输入姓名大于5个汉字时,进行姓名、证件号的模糊查询
			if(buyerName.getBytes("UTF-8").length > 15){
				if(buyerCardcode != null && !"".equals(buyerCardcode)){
					sb.append("   and buyerinfo.buyer_cardcode like ?  ");
					params.add("%" + buyerCardcode + "%");
					sb.append("   or buyerinfo.buyer_cardcode like ?  ");
					params.add("%" + buyerCardCodeN15 + "%");
				}
				sb.append("   and buyerinfo.buyer_name like ?  ");
				params.add("%" + buyerName + "%");
			}else{
				if(buyerCardcode != null && !"".equals(buyerCardcode)){
					sb.append("   and buyerinfo.buyer_cardcode in ('" + buyerCardcode + "','" + buyerCardCodeN15 + "')  ");
				}
				if(buyerName != null && !"".equals(buyerName)){
					sb.append("   and buyerinfo.buyer_name = ?  ");
					params.add(buyerName);
				}
			}
			if(buyerAddress != null && !"".equals(buyerAddress)){
				sb.append("   and buyerinfo.buyer_address like ?  ");
				params.add(buyerAddress);
			}
			if(buyerProxy != null && !"".equals(buyerProxy)){
				sb.append("   and buyerinfo.buyer_proxy = ?  ");
				params.add(buyerProxy);
			}
			if(buyerCardname != null && !"".equals(buyerCardname) && !"-999".equals(buyerCardname)){
				sb.append("   and buyerinfo.buyer_cardname = ?  ");
				params.add(buyerCardname);
			}
			if(buyerType != null && !"".equals(buyerType) && !"-999".equals(buyerType)){
				sb.append("   and buyerinfo.buyer_type = ?  ");
				params.add(buyerType);
			}

			sb.append(" union all ");
			sb.append(" select distinct contractdeal.contract_id,contractdeal.type,contractdeal.signdate,'8' as status,contractdeal.signer,signer.loginname,contractdeal.seller_name,contractdeal.buyer_name,contractdeal.location,buyerinfo.serial as serial ");
			sb.append(" from contractdeal,contractdeal contractdeal2, signer,buyerinfo,presellcontract,earnestcontract,house ");
			sb.append(" where contractdeal.contract_id = buyerinfo.contract_id  ");
			sb.append("   and contractdeal.signer = signer.signer_id  ");
			sb.append("   and contractdeal.contract_id = presellcontract.contract_id  ");
			sb.append("   and presellcontract.earnest_id = earnestcontract.contract_id  ");
			sb.append("   and contractdeal2.contract_id = earnestcontract.contract_id  ");
			sb.append("   and contractdeal2.status = 2  ");
			sb.append("   and contractdeal.status in(0 , 1)  ");
			sb.append("   and house.house_id=contractdeal.house_id  ");
			sb.append(" and house.districtid in " + districtStr);
			if(buyerProvince != null && !"".equals(buyerProvince) && !"-999".equals(buyerProvince)){
				sb.append("   and buyerinfo.buyer_province = ?  ");
				params.add(buyerProvince);
			}
			if(buyerNationality != null && !"".equals(buyerNationality) && !"-999".equals(buyerNationality)){
				sb.append("   and buyerinfo.buyer_nationality = ?  ");
				params.add(buyerNationality);
			}
			if(buyerBirth != null && !"".equals(buyerBirth)){
				sb.append("   and buyerinfo.buyer_birth = ? ");
				params.add(buyerBirth.replaceAll("-", ""));
			}
			//当输入姓名大于5个汉字时,进行姓名、证件号的模糊查询
			if(buyerName.getBytes("UTF-8").length > 15){
				if(buyerCardcode != null && !"".equals(buyerCardcode)){
					sb.append("   and buyerinfo.buyer_cardcode like ?  ");
					params.add("%" + buyerCardcode + "%");
					sb.append("   or buyerinfo.buyer_cardcode like ?  ");
					params.add("%" + buyerCardCodeN15 + "%");
				}
				sb.append("   and buyerinfo.buyer_name like ?  ");
				params.add("%" + buyerName + "%");
			}else{
				if(buyerCardcode != null && !"".equals(buyerCardcode)){
					sb.append("   and buyerinfo.buyer_cardcode in ('" + buyerCardcode + "','" + buyerCardCodeN15 + "')  ");
				}
				if(buyerName != null && !"".equals(buyerName)){
					sb.append("   and buyerinfo.buyer_name = ?  ");
					params.add(buyerName);
				}
			}
			if(buyerAddress != null && !"".equals(buyerAddress)){
				sb.append("   and buyerinfo.buyer_address like ?  ");
				params.add(buyerAddress);
			}
			if(buyerProxy != null && !"".equals(buyerProxy)){
				sb.append("   and buyerinfo.buyer_proxy = ?  ");
				params.add(buyerProxy);
			}
			if(buyerCardname != null && !"".equals(buyerCardname) && !"-999".equals(buyerCardname)){
				sb.append("   and buyerinfo.buyer_cardname = ?  ");
				params.add(buyerCardname);
			}
			if(buyerType != null && !"".equals(buyerType) && !"-999".equals(buyerType)){
				sb.append("   and buyerinfo.buyer_type = ?  ");
				params.add(buyerType);
			}
			List list = DataBaseUtil.select(sb.toString(), params, ContractDealVO.class, page, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}


	/**
	 * 功能描述：合同查询（乙方）列表
	 * @param buyerName
	 * @param buyerCardcode
	 * @param buyerCardCodeN15
	 * @param buyerBirth
	 * @param buyerNationality
	 * @param buyerProxy
	 * @param buyerCardname
	 * @param buyerType
	 * @param buyerProvince
	 * @param buyerAddress
	 * @param districtStr
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<ContractDealVO> queryContractDeal(String buyerName,String buyerCardcode,String buyerCardCodeN15,String buyerBirth,String buyerNationality,String buyerProxy,String buyerCardname,String buyerType,String buyerProvince,String buyerAddress,String districtStr,Page page)
			throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select distinct contractdeal.contract_id,contractdeal.type,contractdeal.signdate,to_char(contractdeal.status) as status,contractdeal.signer,signer.loginname,contractdeal.seller_name,contractdeal.buyer_name,contractdeal.location,buyerinfo.serial as serial ");
			sb.append(" from contractdeal, signer,buyerinfo,house ");
			sb.append(" where  contractdeal.contract_id = buyerinfo.contract_id  ");
			sb.append("  and contractdeal.signer = signer.signer_id  ");
			sb.append("  and contractdeal.status in (2, 4, 5, 7, 10, 11)  ");
			sb.append("  and house.house_id=contractdeal.house_id ");
			sb.append(" and house.districtid in " + districtStr);

			if(buyerProvince != null && !"".equals(buyerProvince) && !"-999".equals(buyerProvince)){
				sb.append("   and buyerinfo.buyer_province = ?  ");
				params.add(buyerProvince);
			}
			if(buyerNationality != null && !"".equals(buyerNationality) && !"-999".equals(buyerNationality)){
				sb.append("   and buyerinfo.buyer_nationality = ?  ");
				params.add(buyerNationality);
			}
			if(buyerBirth != null && !"".equals(buyerBirth)){
				sb.append("   and buyerinfo.buyer_birth = ? ");
				params.add(buyerBirth.replaceAll("-", ""));
			}
			//当输入姓名大于5个汉字时,进行姓名、证件号的模糊查询
			if(buyerName.getBytes("UTF-8").length > 15){
				if(buyerCardcode != null && !"".equals(buyerCardcode)){
					sb.append("   and buyerinfo.buyer_cardcode like ?  ");
					params.add("%" + buyerCardcode + "%");
					sb.append("   or buyerinfo.buyer_cardcode like ?  ");
					params.add("%" + buyerCardCodeN15 + "%");
				}
				sb.append("   and buyerinfo.buyer_name like ?  ");
				params.add("%" + buyerName + "%");
			}else{
				if(buyerCardcode != null && !"".equals(buyerCardcode)){
					sb.append("   and buyerinfo.buyer_cardcode in ('" + buyerCardcode + "','" + buyerCardCodeN15 + "')  ");
				}
				if(buyerName != null && !"".equals(buyerName)){
					sb.append("   and buyerinfo.buyer_name = ?  ");
					params.add(buyerName);
				}
			}
			if(buyerAddress != null && !"".equals(buyerAddress)){
				sb.append("   and buyerinfo.buyer_address like ?  ");
				params.add(buyerAddress);
			}
			if(buyerProxy != null && !"".equals(buyerProxy)){
				sb.append("   and buyerinfo.buyer_proxy = ?  ");
				params.add(buyerProxy);
			}
			if(buyerCardname != null && !"".equals(buyerCardname) && !"-999".equals(buyerCardname)){
				sb.append("   and buyerinfo.buyer_cardname = ?  ");
				params.add(buyerCardname);
			}
			if(buyerType != null && !"".equals(buyerType) && !"-999".equals(buyerType)){
				sb.append("   and buyerinfo.buyer_type = ?  ");
				params.add(buyerType);
			}

			sb.append("  union all ");
			sb.append(" select distinct contractdeal.contract_id,contractdeal.type,contractdeal.signdate,'8' as status,contractdeal.signer,signer.loginname,contractdeal.seller_name,contractdeal.buyer_name,contractdeal.location,buyerinfo.serial as serial ");
			sb.append(" from contractdeal,contractdeal contractdeal2, signer,buyerinfo,sellcontract,earnestcontract,house ");
			sb.append(" where contractdeal.contract_id = buyerinfo.contract_id  ");
			sb.append("   and contractdeal.signer = signer.signer_id  ");
			sb.append("   and contractdeal.contract_id = sellcontract.contract_id  ");
			sb.append("   and sellcontract.earnest_id = earnestcontract.contract_id  ");
			sb.append("   and contractdeal2.contract_id = earnestcontract.contract_id  ");
			sb.append("   and contractdeal2.status = 2  ");
			sb.append("   and contractdeal.status in(0 , 1)  ");
			sb.append("   and house.house_id=contractdeal.house_id  ");
			sb.append(" and house.districtid in " + districtStr);
			if(buyerProvince != null && !"".equals(buyerProvince) && !"-999".equals(buyerProvince)){
				sb.append("   and buyerinfo.buyer_province = ?  ");
				params.add(buyerProvince);
			}
			if(buyerNationality != null && !"".equals(buyerNationality) && !"-999".equals(buyerNationality)){
				sb.append("   and buyerinfo.buyer_nationality = ?  ");
				params.add(buyerNationality);
			}
			if(buyerBirth != null && !"".equals(buyerBirth)){
				sb.append("   and buyerinfo.buyer_birth = ? ");
				params.add(buyerBirth.replaceAll("-", ""));
			}
			//当输入姓名大于5个汉字时,进行姓名、证件号的模糊查询
			if(buyerName.getBytes("UTF-8").length > 15){
				if(buyerCardcode != null && !"".equals(buyerCardcode)){
					sb.append("   and buyerinfo.buyer_cardcode like ?  ");
					params.add("%" + buyerCardcode + "%");
					sb.append("   or buyerinfo.buyer_cardcode like ?  ");
					params.add("%" + buyerCardCodeN15 + "%");
				}
				sb.append("   and buyerinfo.buyer_name like ?  ");
				params.add("%" + buyerName + "%");
			}else{
				if(buyerCardcode != null && !"".equals(buyerCardcode)){
					sb.append("   and buyerinfo.buyer_cardcode in ('" + buyerCardcode + "','" + buyerCardCodeN15 + "')  ");
				}
				if(buyerName != null && !"".equals(buyerName)){
					sb.append("   and buyerinfo.buyer_name = ?  ");
					params.add(buyerName);
				}
			}
			if(buyerAddress != null && !"".equals(buyerAddress)){
				sb.append("   and buyerinfo.buyer_address like ?  ");
				params.add(buyerAddress);
			}
			if(buyerProxy != null && !"".equals(buyerProxy)){
				sb.append("   and buyerinfo.buyer_proxy = ?  ");
				params.add(buyerProxy);
			}
			if(buyerCardname != null && !"".equals(buyerCardname) && !"-999".equals(buyerCardname)){
				sb.append("   and buyerinfo.buyer_cardname = ?  ");
				params.add(buyerCardname);
			}
			if(buyerType != null && !"".equals(buyerType) && !"-999".equals(buyerType)){
				sb.append("   and buyerinfo.buyer_type = ?  ");
				params.add(buyerType);
			}
			
			sb.append(" union all ");
			sb.append(" select distinct contractdeal.contract_id,contractdeal.type,contractdeal.signdate,'8' as status,contractdeal.signer,signer.loginname,contractdeal.seller_name,contractdeal.buyer_name,contractdeal.location,buyerinfo.serial as serial ");
			sb.append(" from contractdeal,contractdeal contractdeal2, signer,buyerinfo,presellcontract,earnestcontract,house ");
			sb.append(" where contractdeal.contract_id = buyerinfo.contract_id  ");
			sb.append("   and contractdeal.signer = signer.signer_id  ");
			sb.append("   and contractdeal.contract_id = presellcontract.contract_id  ");
			sb.append("   and presellcontract.earnest_id = earnestcontract.contract_id  ");
			sb.append("   and contractdeal2.contract_id = earnestcontract.contract_id  ");
			sb.append("   and contractdeal2.status = 2  ");
			sb.append("   and contractdeal.status in(0 , 1)  ");
			sb.append("   and house.house_id=contractdeal.house_id  ");
			sb.append(" and house.districtid in " + districtStr);
			if(buyerProvince != null && !"".equals(buyerProvince) && !"-999".equals(buyerProvince)){
				sb.append("   and buyerinfo.buyer_province = ?  ");
				params.add(buyerProvince);
			}
			if(buyerNationality != null && !"".equals(buyerNationality) && !"-999".equals(buyerNationality)){
				sb.append("   and buyerinfo.buyer_nationality = ?  ");
				params.add(buyerNationality);
			}
			if(buyerBirth != null && !"".equals(buyerBirth)){
				sb.append("   and buyerinfo.buyer_birth = ? ");
				params.add(buyerBirth.replaceAll("-", ""));
			}
			//当输入姓名大于5个汉字时,进行姓名、证件号的模糊查询
			if(buyerName.getBytes("UTF-8").length > 15){
				if(buyerCardcode != null && !"".equals(buyerCardcode)){
					sb.append("   and buyerinfo.buyer_cardcode like ?  ");
					params.add("%" + buyerCardcode + "%");
					sb.append("   or buyerinfo.buyer_cardcode like ?  ");
					params.add("%" + buyerCardCodeN15 + "%");
				}
				sb.append("   and buyerinfo.buyer_name like ?  ");
				params.add("%" + buyerName + "%");
			}else{
				if(buyerCardcode != null && !"".equals(buyerCardcode)){
					sb.append("   and buyerinfo.buyer_cardcode in ('" + buyerCardcode + "','" + buyerCardCodeN15 + "')  ");
				}
				if(buyerName != null && !"".equals(buyerName)){
					sb.append("   and buyerinfo.buyer_name = ?  ");
					params.add(buyerName);
				}
			}
			if(buyerAddress != null && !"".equals(buyerAddress)){
				sb.append("   and buyerinfo.buyer_address like ?  ");
				params.add(buyerAddress);
			}
			if(buyerProxy != null && !"".equals(buyerProxy)){
				sb.append("   and buyerinfo.buyer_proxy = ?  ");
				params.add(buyerProxy);
			}
			if(buyerCardname != null && !"".equals(buyerCardname) && !"-999".equals(buyerCardname)){
				sb.append("   and buyerinfo.buyer_cardname = ?  ");
				params.add(buyerCardname);
			}
			if(buyerType != null && !"".equals(buyerType) && !"-999".equals(buyerType)){
				sb.append("   and buyerinfo.buyer_type = ?  ");
				params.add(buyerType);
			}
			List list = DataBaseUtil.select(sb.toString(), params, ContractDealVO.class, page, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：根据合同编号查询
	 * @param contractID
	 * @return
	 * @throws Exception
	 */
	public List<ContractDealVO> findContractsByContract(Page page,String contractID,String districtList) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();

			sb.append(" select contractdeal.CONTRACT_ID,contractdeal.TYPE,contractdeal.SIGNDATE,to_char(contractdeal.STATUS) as STATUS, ");
			sb.append("	contractdeal.SIGNER,signer.LOGINNAME,contractdeal.SELLER_NAME,contractdeal.BUYER_NAME,contractdeal.location ");
			sb.append("	from CONTRACTDEAL, SIGNER,HOUSE ");
			sb.append("	where CONTRACTDEAL.SIGNER = SIGNER.SIGNER_ID and contractdeal.STATUS IN (2, 4, 5, 7, 10, 11) ");
			sb.append("	and HOUSE.HOUSE_ID=CONTRACTDEAL.HOUSE_ID and HOUSE.DISTRICTID in " + districtList);
			if(contractID != null && !"".equals(contractID)){
				sb.append(" and contractdeal.CONTRACT_ID=? ");
				params.add(contractID);
			}

			sb.append(" union all ");
			sb.append(" select contractdeal.CONTRACT_ID,contractdeal.TYPE,contractdeal.SIGNDATE,'8' as STATUS, ");
			sb.append(" contractdeal.SIGNER,signer.LOGINNAME,contractdeal.SELLER_NAME,contractdeal.BUYER_NAME,contractdeal.location ");
			sb.append(" from CONTRACTDEAL, SIGNER,contractdeal contractdeal2,sellcontract,earnestcontract,HOUSE ");
			sb.append(" where CONTRACTDEAL.SIGNER = SIGNER.SIGNER_ID and contractdeal.CONTRACT_ID = sellcontract.CONTRACT_ID ");
			sb.append(" and sellcontract.EARNEST_ID = earnestcontract.CONTRACT_ID and contractdeal2.CONTRACT_ID = earnestcontract.CONTRACT_ID ");
			sb.append(" and contractdeal2.STATUS = 2 and contractdeal.STATUS in(0 , 1) ");
			sb.append(" and HOUSE.HOUSE_ID=CONTRACTDEAL.HOUSE_ID and HOUSE.DISTRICTID in " + districtList);
			if(contractID != null && !"".equals(contractID)){
				sb.append(" and contractdeal.CONTRACT_ID=? ");
				params.add(contractID);
			}

			sb.append(" union all ");
			sb.append("	select contractdeal.CONTRACT_ID,contractdeal.TYPE,contractdeal.SIGNDATE,'8' as STATUS, ");
			sb.append("	contractdeal.SIGNER,signer.LOGINNAME,contractdeal.SELLER_NAME,contractdeal.BUYER_NAME,contractdeal.location ");
			sb.append("	from contractdeal, signer,contractdeal contractdeal2,presellcontract,earnestcontract,house ");
			sb.append("	where CONTRACTDEAL.SIGNER = SIGNER.SIGNER_ID and contractdeal.CONTRACT_ID = presellcontract.CONTRACT_ID ");
			sb.append("	and presellcontract.EARNEST_ID = earnestcontract.CONTRACT_ID and contractdeal2.CONTRACT_ID = earnestcontract.CONTRACT_ID ");
			sb.append("	and contractdeal2.STATUS = 2 and contractdeal.STATUS in(0 , 1) ");
			sb.append("	and HOUSE.HOUSE_ID=CONTRACTDEAL.HOUSE_ID and HOUSE.DISTRICTID in " + districtList);
			if(contractID != null && !"".equals(contractID)){
				sb.append(" and contractdeal.CONTRACT_ID=? ");
				params.add(contractID);
			}
			//System.out.println("sql=" + sb.toString());
			List list = DataBaseUtil.select(sb.toString(), params, ContractDealVO.class, page, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：根据项目信息查询
	 * @param startID
	 * @param projectName
	 * @param signDate1
	 * @param signDate2
	 * @param type
	 * @param confirmDate1
	 * @param confirmDate2
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public List<ContractDealVO> findContractsByProject(Page page,String startID,String projectName,String signDate1,String signDate2,String type,String confirmDate1,String confirmDate2,String status,String district,String road,String laneName,String subLane,String buildingNumber,
			String districtList)
			throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			if(status == null || "".equals(status) || "-999".equals(status)){
				sb.append(" select contractdeal.CONTRACT_ID,contractdeal.TYPE,contractdeal.SIGNDATE,to_char(contractdeal.STATUS) as STATUS, ");
				sb.append(" contractdeal.SIGNER,signer.LOGINNAME,contractdeal.SELLER_NAME,contractdeal.BUYER_NAME,contractdeal.location ");
				sb.append(" from contractdeal, PROJECT, start_unit,signer ");
				sb.append(" where PROJECT.project_id=contractdeal.project_id and contractdeal.start_id = start_unit.start_id ");
				sb.append(" and contractdeal.SIGNER = signer.SIGNER_ID and contractdeal.STATUS IN (1, 2, 4, 5, 7, 10, 11) ");
				sb.append(" and PROJECT.DISTRICTID in " + districtList);
				if(district != null && !"".equals(district) && !"-999".equals(district)){
					sb.append(" and PROJECT.districtid = ? ");
					params.add(district);
				}
				if(startID != null && !"".equals(startID)){
					sb.append(" and start_unit.start_code =?");
					params.add(startID);
				}
				if(projectName != null && !"".equals(projectName)){
					sb.append(" and PROJECT.PROJECTNAME like ?");
					params.add("%" + projectName + "%");
				}
				if(signDate1 != null && !"".equals(signDate1)){
					sb.append(" and contractdeal.SIGNDATE >=? ");
					String a = signDate1.replaceAll("-", "");
					a += "000000";
					params.add(a);
				}
				if(signDate2 != null && !"".equals(signDate2)){
					sb.append(" and contractdeal.SIGNDATE <=? ");
					String a = signDate2.replaceAll("-", "");
					a += "235959";
					params.add(a);
				}
				if(type != null && !"".equals(type)){
					sb.append(" and contractdeal.TYPE=? ");
					params.add(type);
				}
				if(confirmDate1 != null && !"".equals(confirmDate1)){
					sb.append(" and contractdeal.CONFIRMDATE >=? ");
					String a = confirmDate1.replaceAll("-", "");
					a += "000000";
					params.add(a);
				}
				if(confirmDate2 != null && !"".equals(confirmDate2)){
					sb.append(" and contractdeal.CONFIRMDATE <=? ");
					String a = confirmDate2.replaceAll("-", "");
					a += "235959";
					params.add(a);
				}
				if(status != null && !"".equals(status) && !"-999".equals(status)){
					sb.append(" and contractdeal.STATUS = ? ");
					params.add(status);
				}
				if(road != null && !"".equals(road)){
					sb.append(" and contractdeal.ROAD like ? ");
					params.add("%" + road + "%");
				}
				if(laneName != null && !"".equals(laneName)){
					sb.append(" and contractdeal.LANE_NAME like ? ");
					params.add("%" + laneName + "%");
				}
				if(subLane != null && !"".equals(subLane)){
					sb.append(" and contractdeal.SUB_LANE like ? ");
					params.add("%" + subLane + "%");
				}
				if(buildingNumber != null && !"".equals(buildingNumber)){
					sb.append(" and contractdeal.BUILDINGNUMBER like ?");
					params.add("%" + buildingNumber + "%");
				}

				sb.append(" union all ");
				sb.append(" select contractdeal.CONTRACT_ID,contractdeal.TYPE,contractdeal.SIGNDATE,'8' as STATUS, ");
				sb.append(" contractdeal.SIGNER,signer.LOGINNAME,contractdeal.SELLER_NAME,contractdeal.BUYER_NAME,contractdeal.LOCATION ");
				sb.append(" from contractdeal,contractdeal contractdeal2,PROJECT,start_unit, signer, sellcontract,earnestcontract ");
				sb.append(" where PROJECT.project_id=contractdeal.project_id and contractdeal.start_id = start_unit.start_id ");
				sb.append(" and contractdeal.SIGNER = signer.SIGNER_ID and contractdeal.CONTRACT_ID = sellcontract.CONTRACT_ID ");
				sb.append(" and sellcontract.EARNEST_ID = earnestcontract.CONTRACT_ID and contractdeal2.CONTRACT_ID = earnestcontract.CONTRACT_ID ");
				sb.append(" and contractdeal2.STATUS = 2 and contractdeal.STATUS in(0 , 1) and PROJECT.DISTRICTID in " + districtList);
				if(district != null && !"".equals(district) && !"-999".equals(district)){
					sb.append(" and PROJECT.districtid = ? ");
					params.add(district);
				}
				if(startID != null && !"".equals(startID)){
					sb.append(" and start_unit.start_code =?");
					params.add(startID);
				}
				if(projectName != null && !"".equals(projectName)){
					sb.append(" and PROJECT.PROJECTNAME like ?");
					params.add("%" + projectName + "%");
				}
				if(signDate1 != null && !"".equals(signDate1)){
					sb.append(" and contractdeal.SIGNDATE >=? ");
					String a = signDate1.replaceAll("-", "");
					a += "000000";
					params.add(a);
				}
				if(signDate2 != null && !"".equals(signDate2)){
					sb.append(" and contractdeal.SIGNDATE <=? ");
					String a = signDate2.replaceAll("-", "");
					a += "235959";
					params.add(a);
				}
				if(type != null && !"".equals(type)){
					sb.append(" and contractdeal.TYPE=? ");
					params.add(type);
				}
				if(confirmDate1 != null && !"".equals(confirmDate1)){
					sb.append(" and contractdeal.CONFIRMDATE >=? ");
					String a = confirmDate1.replaceAll("-", "");
					a += "000000";
					params.add(a);
				}
				if(confirmDate2 != null && !"".equals(confirmDate2)){
					sb.append(" and contractdeal.CONFIRMDATE <=? ");
					String a = confirmDate2.replaceAll("-", "");
					a += "235959";
					params.add(a);
				}
				if(road != null && !"".equals(road)){
					sb.append(" and contractdeal.ROAD like ? ");
					params.add("%" + road + "%");
				}
				if(laneName != null && !"".equals(laneName)){
					sb.append(" and contractdeal.LANE_NAME like ? ");
					params.add("%" + laneName + "%");
				}
				if(subLane != null && !"".equals(subLane)){
					sb.append(" and contractdeal.SUB_LANE like ? ");
					params.add("%" + subLane + "%");
				}
				if(buildingNumber != null && !"".equals(buildingNumber)){
					sb.append(" and contractdeal.BUILDINGNUMBER like ?");
					params.add("%" + buildingNumber + "%");
				}

				sb.append(" union all ");
				sb.append(" select contractdeal.CONTRACT_ID,contractdeal.TYPE,contractdeal.SIGNDATE,'8' as STATUS, ");
				sb.append(" contractdeal.SIGNER,signer.LOGINNAME,contractdeal.SELLER_NAME,contractdeal.BUYER_NAME,contractdeal.LOCATION ");
				sb.append(" from contractdeal,contractdeal contractdeal2,PROJECT,start_unit, signer, sellcontract,earnestcontract ");
				sb.append(" where PROJECT.project_id=contractdeal.project_id and contractdeal.start_id = start_unit.start_id ");
				sb.append(" and contractdeal.SIGNER = signer.SIGNER_ID and contractdeal.CONTRACT_ID = sellcontract.CONTRACT_ID ");
				sb.append(" and sellcontract.EARNEST_ID = earnestcontract.CONTRACT_ID and contractdeal2.CONTRACT_ID = earnestcontract.CONTRACT_ID ");
				sb.append(" and contractdeal2.STATUS = 2 and contractdeal.STATUS in(0 , 1) and PROJECT.DISTRICTID in " + districtList);
				if(district != null && !"".equals(district) && !"-999".equals(district)){
					sb.append(" and PROJECT.districtid = ? ");
					params.add(district);
				}
				if(startID != null && !"".equals(startID)){
					sb.append(" and start_unit.start_code =?");
					params.add(startID);
				}
				if(projectName != null && !"".equals(projectName)){
					sb.append(" and PROJECT.PROJECTNAME like ?");
					params.add("%" + projectName + "%");
				}
				if(signDate1 != null && !"".equals(signDate1)){
					sb.append(" and contractdeal.SIGNDATE >=? ");
					String a = signDate1.replaceAll("-", "");
					a += "000000";
					params.add(a);
				}
				if(signDate2 != null && !"".equals(signDate2)){
					sb.append(" and contractdeal.SIGNDATE <=? ");
					String a = signDate2.replaceAll("-", "");
					a += "235959";
					params.add(a);
				}
				if(type != null && !"".equals(type)){
					sb.append(" and contractdeal.TYPE=? ");
					params.add(type);
				}
				if(confirmDate1 != null && !"".equals(confirmDate1)){
					sb.append(" and contractdeal.CONFIRMDATE >=? ");
					String a = confirmDate1.replaceAll("-", "");
					a += "000000";
					params.add(a);
				}
				if(confirmDate2 != null && !"".equals(confirmDate2)){
					sb.append(" and contractdeal.CONFIRMDATE <=? ");
					String a = confirmDate2.replaceAll("-", "");
					a += "235959";
					params.add(a);
				}
				if(road != null && !"".equals(road)){
					sb.append(" and contractdeal.ROAD like ? ");
					params.add(road);
				}
				if(laneName != null && !"".equals(laneName)){
					sb.append(" and contractdeal.LANE_NAME like ? ");
					params.add("%" + laneName + "%");
				}
				if(subLane != null && !"".equals(subLane)){
					sb.append(" and contractdeal.SUB_LANE like ? ");
					params.add("%" + subLane + "%");
				}
				if(buildingNumber != null && !"".equals(buildingNumber)){
					sb.append(" and contractdeal.BUILDINGNUMBER like ?");
					params.add("%" + buildingNumber + "%");
				}
			}
			if("8".equals(status)){
				sb.append(" select contractdeal.CONTRACT_ID,contractdeal.TYPE,contractdeal.SIGNDATE,'8' as STATUS, ");
				sb.append(" contractdeal.SIGNER,signer.LOGINNAME,contractdeal.SELLER_NAME,contractdeal.BUYER_NAME,contractdeal.LOCATION ");
				sb.append(" from contractdeal,contractdeal contractdeal2,PROJECT,start_unit, signer,sellcontract,earnestcontract ");
				sb.append(" where PROJECT.project_id=contractdeal.project_id and contractdeal.start_id = start_unit.start_id ");
				sb.append(" and contractdeal.SIGNER = signer.SIGNER_ID and contractdeal.CONTRACT_ID = sellcontract.CONTRACT_ID ");
				sb.append(" and sellcontract.EARNEST_ID = earnestcontract.CONTRACT_ID and contractdeal2.CONTRACT_ID = earnestcontract.CONTRACT_ID ");
				sb.append(" and contractdeal2.STATUS = 2 and contractdeal.STATUS in(0 , 1) and PROJECT.DISTRICTID in " + districtList);
				if(district != null && !"".equals(district) && !"-999".equals(district)){
					sb.append(" and PROJECT.districtid = ? ");
					params.add(district);
				}
				if(startID != null && !"".equals(startID)){
					sb.append(" and start_unit.start_code =?");
					params.add(startID);
				}
				if(projectName != null && !"".equals(projectName)){
					sb.append(" and PROJECT.PROJECTNAME like ?");
					params.add("%" + projectName + "%");
				}
				if(signDate1 != null && !"".equals(signDate1)){
					sb.append(" and contractdeal.SIGNDATE >=? ");
					String a = signDate1.replaceAll("-", "");
					a += "000000";
					params.add(a);
				}
				if(signDate2 != null && !"".equals(signDate2)){
					sb.append(" and contractdeal.SIGNDATE <=? ");
					String a = signDate2.replaceAll("-", "");
					a += "235959";
					params.add(a);
				}
				if(type != null && !"".equals(type)){
					sb.append(" and contractdeal.TYPE=? ");
					params.add(type);
				}
				if(confirmDate1 != null && !"".equals(confirmDate1)){
					sb.append(" and contractdeal.CONFIRMDATE >=? ");
					String a = confirmDate1.replaceAll("-", "");
					a += "000000";
					params.add(a);
				}
				if(confirmDate2 != null && !"".equals(confirmDate2)){
					sb.append(" and contractdeal.CONFIRMDATE <=? ");
					String a = confirmDate2.replaceAll("-", "");
					a += "235959";
					params.add(a);
				}
				if(road != null && !"".equals(road)){
					sb.append(" and contractdeal.ROAD like ? ");
					params.add("%" + road + "%");
				}
				if(laneName != null && !"".equals(laneName)){
					sb.append(" and contractdeal.LANE_NAME like ? ");
					params.add("%" + laneName + "%");
				}
				if(subLane != null && !"".equals(subLane)){
					sb.append(" and contractdeal.SUB_LANE like ? ");
					params.add("%" + subLane + "%");
				}
				if(buildingNumber != null && !"".equals(buildingNumber)){
					sb.append(" and contractdeal.BUILDINGNUMBER like ?");
					params.add("%" + buildingNumber + "%");
				}

				sb.append(" union all ");
				sb.append(" select contractdeal.CONTRACT_ID,contractdeal.TYPE,contractdeal.SIGNDATE,'8' as STATUS, ");
				sb.append(" contractdeal.SIGNER,signer.LOGINNAME,contractdeal.SELLER_NAME,contractdeal.BUYER_NAME,contractdeal.LOCATION ");
				sb.append(" from contractdeal,contractdeal contractdeal2,PROJECT,start_unit, signer,sellcontract,earnestcontract ");
				sb.append(" where PROJECT.project_id=contractdeal.project_id and contractdeal.start_id = start_unit.start_id ");
				sb.append(" and contractdeal.SIGNER = signer.SIGNER_ID and contractdeal.CONTRACT_ID = sellcontract.CONTRACT_ID ");
				sb.append(" and sellcontract.EARNEST_ID = earnestcontract.CONTRACT_ID and contractdeal2.CONTRACT_ID = earnestcontract.CONTRACT_ID ");
				sb.append(" and contractdeal2.STATUS = 2 and contractdeal.STATUS in(0 , 1) and PROJECT.DISTRICTID in " + districtList);
				if(district != null && !"".equals(district) && !"-999".equals(district)){
					sb.append(" and PROJECT.districtid = ? ");
					params.add(district);
				}
				if(startID != null && !"".equals(startID)){
					sb.append(" and start_unit.start_code =?");
					params.add(startID);
				}
				if(projectName != null && !"".equals(projectName)){
					sb.append(" and PROJECT.PROJECTNAME like ?");
					params.add("%" + projectName + "%");
				}
				if(signDate1 != null && !"".equals(signDate1)){
					sb.append(" and contractdeal.SIGNDATE >=? ");
					String a = signDate1.replaceAll("-", "");
					a += "000000";
					params.add(a);
				}
				if(signDate2 != null && !"".equals(signDate2)){
					sb.append(" and contractdeal.SIGNDATE <=? ");
					String a = signDate2.replaceAll("-", "");
					a += "235959";
					params.add(a);
				}
				if(type != null && !"".equals(type)){
					sb.append(" and contractdeal.TYPE=? ");
					params.add(type);
				}
				if(confirmDate1 != null && !"".equals(confirmDate1)){
					sb.append(" and contractdeal.CONFIRMDATE >=? ");
					String a = confirmDate1.replaceAll("-", "");
					a += "000000";
					params.add(a);
				}
				if(confirmDate2 != null && !"".equals(confirmDate2)){
					sb.append(" and contractdeal.CONFIRMDATE <=? ");
					String a = confirmDate2.replaceAll("-", "");
					a += "235959";
					params.add(a);
				}
				if(road != null && !"".equals(road)){
					sb.append(" and contractdeal.ROAD like ? ");
					params.add("%" + road + "%");
				}
				if(laneName != null && !"".equals(laneName)){
					sb.append(" and contractdeal.LANE_NAME like ? ");
					params.add("%" + laneName + "%");
				}
				if(subLane != null && !"".equals(subLane)){
					sb.append(" and contractdeal.SUB_LANE like ? ");
					params.add("%" + subLane + "%");
				}
				if(buildingNumber != null && !"".equals(buildingNumber)){
					sb.append(" and contractdeal.BUILDINGNUMBER like ?");
					params.add("%" + buildingNumber + "%");
				}
			}
			if(status != null && !"".equals(status) && !"-999".equals(status) && Integer.parseInt(status) != 8){
				sb.append(" select contractdeal.CONTRACT_ID,contractdeal.TYPE,contractdeal.SIGNDATE,to_char(contractdeal.STATUS) as STATUS, ");
				sb.append(" contractdeal.SIGNER,signer.LOGINNAME,contractdeal.SELLER_NAME,contractdeal.BUYER_NAME,contractdeal.location ");
				sb.append(" from contractdeal, PROJECT, start_unit,signer ");
				sb.append(" where PROJECT.project_id=contractdeal.project_id and contractdeal.start_id = start_unit.start_id ");
				sb.append(" and contractdeal.SIGNER = signer.SIGNER_ID and contractdeal.STATUS IN (2, 4, 5, 7, 10, 11) ");
				sb.append(" and PROJECT.DISTRICTID in " + districtList);
				if(district != null && !"".equals(district) && !"-999".equals(district)){
					sb.append(" and PROJECT.districtid = ? ");
					params.add(district);
				}
				if(startID != null && !"".equals(startID)){
					sb.append(" and start_unit.start_code =?");
					params.add(startID);
				}
				if(projectName != null && !"".equals(projectName)){
					sb.append(" and PROJECT.PROJECTNAME like ?");
					params.add("%" + projectName + "%");
				}
				if(signDate1 != null && !"".equals(signDate1)){
					sb.append(" and contractdeal.SIGNDATE >=? ");
					String a = signDate1.replaceAll("-", "");
					a += "000000";
					params.add(a);
				}
				if(signDate2 != null && !"".equals(signDate2)){
					sb.append(" and contractdeal.SIGNDATE <=? ");
					String a = signDate2.replaceAll("-", "");
					a += "235959";
					params.add(a);
				}
				if(type != null && !"".equals(type)){
					sb.append(" and contractdeal.TYPE=? ");
					params.add(type);
				}
				if(confirmDate1 != null && !"".equals(confirmDate1)){
					sb.append(" and contractdeal.CONFIRMDATE >=? ");
					String a = confirmDate1.replaceAll("-", "");
					a += "000000";
					params.add(a);
				}
				if(confirmDate2 != null && !"".equals(confirmDate2)){
					sb.append(" and contractdeal.CONFIRMDATE <=? ");
					String a = confirmDate2.replaceAll("-", "");
					a += "235959";
					params.add(a);
				}
				if(status != null && !"".equals(status) && !"-999".equals(status)){
					sb.append(" and contractdeal.STATUS = ? ");
					params.add(status);
				}
				if(road != null && !"".equals(road)){
					sb.append(" and contractdeal.ROAD like ? ");
					params.add("%" + road + "%");
				}
				if(laneName != null && !"".equals(laneName)){
					sb.append(" and contractdeal.LANE_NAME like ? ");
					params.add("%" + laneName + "%");
				}
				if(subLane != null && !"".equals(subLane)){
					sb.append(" and contractdeal.SUB_LANE like ? ");
					params.add("%" + subLane + "%");
				}
				if(buildingNumber != null && !"".equals(buildingNumber)){
					sb.append(" and contractdeal.BUILDINGNUMBER like ?");
					params.add("%" + buildingNumber + "%");
				}
			}
			//System.out.println("sql=" + sb.toString());
			List list = DataBaseUtil.select(sb.toString(), params, ContractDealVO.class, page, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：根据甲方信息查询
	 * @param startID
	 * @param projectName
	 * @param signDate1
	 * @param signDate2
	 * @param type
	 * @param confirmDate1
	 * @param confirmDate2
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public List<ContractDealVO> findContractsBySeller(Page page,String sellerName,String compCode,String sellerAddress,String sellerCertcode,String sellerDlgCall,String sellerDelegate,String sellerProxy,String signDate1,String signDate2,String type,String confirmDate1,String confirmDate2,
			String status,String districtList) throws Exception {
		try{
			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			if(status == null || "".equals(status) || "-999".equals(status)){
				sb.append(" select contractdeal.CONTRACT_ID,contractdeal.TYPE,contractdeal.SIGNDATE,to_char(contractdeal.STATUS) as STATUS, ");
				sb.append(" contractdeal.SIGNER,signer.LOGINNAME,contractdeal.SELLER_NAME,contractdeal.BUYER_NAME,contractdeal.location ");
				sb.append(" from contractdeal, sellerinfo, signer,enterprisequalify ");
				sb.append(" where contractdeal.CONTRACT_ID = sellerinfo.CONTRACT_ID and contractdeal.SIGNER = signer.SIGNER_ID ");
				sb.append(" and enterprisequalify.COMP_ID = sellerinfo.COMP_ID and contractdeal.STATUS IN (1, 2, 4, 5, 7, 10, 11) ");
				sb.append(" and enterprisequalify.BIZDISTRICT in " + districtList);
				if(sellerName != null && !"".equals(sellerName)){
					sb.append(" and sellerinfo.SELLER_NAME like ? ");
					params.add("%" + sellerName + "%");
				}
				if(compCode != null && !"".equals(compCode)){
					sb.append(" and enterprisequalify.comp_code =? ");
					params.add(compCode);
				}
				if(signDate1 != null && !"".equals(signDate1)){
					sb.append(" and contractdeal.SIGNDATE >=? ");
					String a = signDate1.replaceAll("-", "");
					a += "000000";
					params.add(a);
				}
				if(signDate2 != null && !"".equals(signDate2)){
					sb.append(" and contractdeal.SIGNDATE <=? ");
					String a = signDate2.replaceAll("-", "");
					a += "000000";
					params.add(a);
				}
				if(type != null && !"".equals(type)){
					sb.append(" and contractdeal.TYPE=? ");
					params.add(type);
				}
				if(confirmDate1 != null && !"".equals(confirmDate1)){
					sb.append(" and contractdeal.CONFIRMDATE >=? ");
					String a = confirmDate1.replaceAll("-", "");
					a += "000000";
					params.add(a);
				}
				if(confirmDate2 != null && !"".equals(confirmDate2)){
					sb.append(" and contractdeal.CONFIRMDATE <=? ");
					String a = confirmDate2.replaceAll("-", "");
					a += "000000";
					params.add(a);
				}
				if(status != null && !"".equals(status) && !"-999".equals(status)){
					sb.append(" and contractdeal.STATUS = ? ");
					params.add(status);
				}
				if(sellerAddress != null && !"".equals(sellerAddress)){
					sb.append(" and sellerinfo.SELLER_ADDRESS like ? ");
					params.add("%" + sellerAddress + "%");
				}
				if(sellerCertcode != null && !"".equals(sellerCertcode)){
					sb.append(" and sellerinfo.SELLER_CERTCODE =? ");
					params.add(sellerCertcode);
				}
				if(sellerDlgCall != null && !"".equals(sellerDlgCall)){
					sb.append(" and sellerinfo.SELLER_DLG_CALL =? ");
					params.add(sellerDlgCall);
				}
				if(sellerDelegate != null && !"".equals(sellerDelegate)){
					sb.append(" and sellerinfo.SELLER_DELEGATE like ? ");
					params.add("%" + sellerDelegate + "%");
				}
				if(sellerProxy != null && !"".equals(sellerProxy)){
					sb.append(" and sellerinfo.SELLER_PROXY like ? ");
					params.add("%" + sellerProxy + "%");
				}

				sb.append(" UNION ALL ");
				sb.append(" select contractdeal.CONTRACT_ID,contractdeal.TYPE,contractdeal.SIGNDATE,'8' as STATUS, ");
				sb.append(" contractdeal.SIGNER,signer.LOGINNAME,contractdeal.SELLER_NAME,contractdeal.BUYER_NAME,contractdeal.location ");
				sb.append(" from contractdeal, sellerinfo, signer,enterprisequalify,contractdeal contractdeal2,sellcontract,earnestcontract ");
				sb.append(" where contractdeal.CONTRACT_ID = sellerinfo.CONTRACT_ID and contractdeal.SIGNER = signer.SIGNER_ID  ");
				sb.append(" and enterprisequalify.COMP_ID = sellerinfo.COMP_ID and contractdeal.CONTRACT_ID = sellcontract.CONTRACT_ID ");
				sb.append(" and sellcontract.EARNEST_ID = earnestcontract.CONTRACT_ID and contractdeal2.CONTRACT_ID = earnestcontract.CONTRACT_ID ");
				sb.append(" and contractdeal2.STATUS = 2 and contractdeal.STATUS in(0 , 1) and enterprisequalify.BIZDISTRICT in " + districtList);
				if(sellerName != null && !"".equals(sellerName)){
					sb.append(" and sellerinfo.SELLER_NAME like ? ");
					params.add("%" + sellerName + "%");
				}
				if(compCode != null && !"".equals(compCode)){
					sb.append(" and enterprisequalify.comp_code =? ");
					params.add(compCode);
				}
				if(signDate1 != null && !"".equals(signDate1)){
					sb.append(" and contractdeal.SIGNDATE >=? ");
					String a = signDate1.replaceAll("-", "");
					a += "000000";
					params.add(a);
				}
				if(signDate2 != null && !"".equals(signDate2)){
					sb.append(" and contractdeal.SIGNDATE <=? ");
					String a = signDate2.replaceAll("-", "");
					a += "000000";
					params.add(a);
				}
				if(type != null && !"".equals(type)){
					sb.append(" and contractdeal.TYPE=? ");
					params.add(type);
				}
				if(confirmDate1 != null && !"".equals(confirmDate1)){
					sb.append(" and contractdeal.CONFIRMDATE >=? ");
					String a = confirmDate1.replaceAll("-", "");
					a += "000000";
					params.add(a);
				}
				if(confirmDate2 != null && !"".equals(confirmDate2)){
					sb.append(" and contractdeal.CONFIRMDATE <=? ");
					String a = confirmDate2.replaceAll("-", "");
					a += "000000";
					params.add(a);
				}
				if(sellerAddress != null && !"".equals(sellerAddress)){
					sb.append(" and sellerinfo.SELLER_ADDRESS like ? ");
					params.add("%" + sellerAddress + "%");
				}
				if(sellerCertcode != null && !"".equals(sellerCertcode)){
					sb.append(" and sellerinfo.SELLER_CERTCODE =? ");
					params.add(sellerCertcode);
				}
				if(sellerDlgCall != null && !"".equals(sellerDlgCall)){
					sb.append(" and sellerinfo.SELLER_DLG_CALL =? ");
					params.add(sellerDlgCall);
				}
				if(sellerDelegate != null && !"".equals(sellerDelegate)){
					sb.append(" and sellerinfo.SELLER_DELEGATE like ? ");
					params.add("%" + sellerDelegate + "%");
				}
				if(sellerProxy != null && !"".equals(sellerProxy)){
					sb.append(" and sellerinfo.SELLER_PROXY like ? ");
					params.add("%" + sellerProxy + "%");
				}

				sb.append(" UNION ALL ");
				sb.append(" select contractdeal.CONTRACT_ID,contractdeal.TYPE,contractdeal.SIGNDATE,'8' as STATUS, ");
				sb.append(" contractdeal.SIGNER,signer.LOGINNAME,contractdeal.SELLER_NAME,contractdeal.BUYER_NAME,contractdeal.location ");
				sb.append(" from contractdeal, sellerinfo, signer,enterprisequalify,contractdeal contractdeal2,sellcontract,earnestcontract ");
				sb.append(" where contractdeal.CONTRACT_ID = sellerinfo.CONTRACT_ID and contractdeal.SIGNER = signer.SIGNER_ID  ");
				sb.append(" and enterprisequalify.COMP_ID = sellerinfo.COMP_ID and contractdeal.CONTRACT_ID = sellcontract.CONTRACT_ID ");
				sb.append(" and sellcontract.EARNEST_ID = earnestcontract.CONTRACT_ID and contractdeal2.CONTRACT_ID = earnestcontract.CONTRACT_ID ");
				sb.append(" and contractdeal2.STATUS = 2 and contractdeal.STATUS in(0 , 1) and enterprisequalify.BIZDISTRICT in " + districtList);
				if(sellerName != null && !"".equals(sellerName)){
					sb.append(" and sellerinfo.SELLER_NAME like ? ");
					params.add("%" + sellerName + "%");
				}
				if(compCode != null && !"".equals(compCode)){
					sb.append(" and enterprisequalify.comp_code =? ");
					params.add(compCode);
				}
				if(signDate1 != null && !"".equals(signDate1)){
					sb.append(" and contractdeal.SIGNDATE >=? ");
					String a = signDate1.replaceAll("-", "");
					a += "000000";
					params.add(a);
				}
				if(signDate2 != null && !"".equals(signDate2)){
					sb.append(" and contractdeal.SIGNDATE <=? ");
					String a = signDate2.replaceAll("-", "");
					a += "000000";
					params.add(a);
				}
				if(type != null && !"".equals(type)){
					sb.append(" and contractdeal.TYPE=? ");
					params.add(type);
				}
				if(confirmDate1 != null && !"".equals(confirmDate1)){
					sb.append(" and contractdeal.CONFIRMDATE >=? ");
					String a = confirmDate1.replaceAll("-", "");
					a += "000000";
					params.add(a);
				}
				if(confirmDate2 != null && !"".equals(confirmDate2)){
					sb.append(" and contractdeal.CONFIRMDATE <=? ");
					String a = confirmDate2.replaceAll("-", "");
					a += "000000";
					params.add(a);
				}
				if(sellerAddress != null && !"".equals(sellerAddress)){
					sb.append(" and sellerinfo.SELLER_ADDRESS like ? ");
					params.add("%" + sellerAddress + "%");
				}
				if(sellerCertcode != null && !"".equals(sellerCertcode)){
					sb.append(" and sellerinfo.SELLER_CERTCODE =? ");
					params.add(sellerCertcode);
				}
				if(sellerDlgCall != null && !"".equals(sellerDlgCall)){
					sb.append(" and sellerinfo.SELLER_DLG_CALL =? ");
					params.add(sellerDlgCall);
				}
				if(sellerDelegate != null && !"".equals(sellerDelegate)){
					sb.append(" and sellerinfo.SELLER_DELEGATE like ? ");
					params.add("%" + sellerDelegate + "%");
				}
				if(sellerProxy != null && !"".equals(sellerProxy)){
					sb.append(" and sellerinfo.SELLER_PROXY like ? ");
					params.add("%" + sellerProxy + "%");
				}
			}
			if("8".equals(status)){
				sb.append(" select contractdeal.CONTRACT_ID,contractdeal.TYPE,contractdeal.SIGNDATE,'8' as STATUS, ");
				sb.append(" contractdeal.SIGNER,signer.LOGINNAME,contractdeal.SELLER_NAME,contractdeal.BUYER_NAME,contractdeal.location ");
				sb.append(" from contractdeal, sellerinfo, signer,enterprisequalify,contractdeal contractdeal2,sellcontract,earnestcontract ");
				sb.append(" where contractdeal.CONTRACT_ID = sellerinfo.CONTRACT_ID and contractdeal.SIGNER = signer.SIGNER_ID  ");
				sb.append(" and enterprisequalify.COMP_ID = sellerinfo.COMP_ID and contractdeal.CONTRACT_ID = sellcontract.CONTRACT_ID ");
				sb.append(" and sellcontract.EARNEST_ID = earnestcontract.CONTRACT_ID and contractdeal2.CONTRACT_ID = earnestcontract.CONTRACT_ID ");
				sb.append(" and contractdeal2.STATUS = 2 and contractdeal.STATUS in(0 , 1) and enterprisequalify.BIZDISTRICT in " + districtList);
				if(sellerName != null && !"".equals(sellerName)){
					sb.append(" and sellerinfo.SELLER_NAME like ? ");
					params.add("%" + sellerName + "%");
				}
				if(compCode != null && !"".equals(compCode)){
					sb.append(" and enterprisequalify.comp_code =? ");
					params.add(compCode);
				}
				if(signDate1 != null && !"".equals(signDate1)){
					sb.append(" and contractdeal.SIGNDATE >=? ");
					String a = signDate1.replaceAll("-", "");
					a += "000000";
					params.add(a);
				}
				if(signDate2 != null && !"".equals(signDate2)){
					sb.append(" and contractdeal.SIGNDATE <=? ");
					String a = signDate2.replaceAll("-", "");
					a += "000000";
					params.add(a);
				}
				if(type != null && !"".equals(type)){
					sb.append(" and contractdeal.TYPE=? ");
					params.add(type);
				}
				if(confirmDate1 != null && !"".equals(confirmDate1)){
					sb.append(" and contractdeal.CONFIRMDATE >=? ");
					String a = confirmDate1.replaceAll("-", "");
					a += "000000";
					params.add(a);
				}
				if(confirmDate2 != null && !"".equals(confirmDate2)){
					sb.append(" and contractdeal.CONFIRMDATE <=? ");
					String a = confirmDate2.replaceAll("-", "");
					a += "000000";
					params.add(a);
				}
				if(sellerAddress != null && !"".equals(sellerAddress)){
					sb.append(" and sellerinfo.SELLER_ADDRESS like ? ");
					params.add("%" + sellerAddress + "%");
				}
				if(sellerCertcode != null && !"".equals(sellerCertcode)){
					sb.append(" and sellerinfo.SELLER_CERTCODE =? ");
					params.add(sellerCertcode);
				}
				if(sellerDlgCall != null && !"".equals(sellerDlgCall)){
					sb.append(" and sellerinfo.SELLER_DLG_CALL =? ");
					params.add(sellerDlgCall);
				}
				if(sellerDelegate != null && !"".equals(sellerDelegate)){
					sb.append(" and sellerinfo.SELLER_DELEGATE like ? ");
					params.add("%" + sellerDelegate + "%");
				}
				if(sellerProxy != null && !"".equals(sellerProxy)){
					sb.append(" and sellerinfo.SELLER_PROXY like ? ");
					params.add("%" + sellerProxy + "%");
				}

				sb.append(" UNION ALL ");
				sb.append(" select contractdeal.CONTRACT_ID,contractdeal.TYPE,contractdeal.SIGNDATE,'8' as STATUS, ");
				sb.append(" contractdeal.SIGNER,signer.LOGINNAME,contractdeal.SELLER_NAME,contractdeal.BUYER_NAME,contractdeal.location ");
				sb.append(" from contractdeal, sellerinfo, signer,enterprisequalify,contractdeal contractdeal2,sellcontract,earnestcontract ");
				sb.append(" where contractdeal.CONTRACT_ID = sellerinfo.CONTRACT_ID and contractdeal.SIGNER = signer.SIGNER_ID  ");
				sb.append(" and enterprisequalify.COMP_ID = sellerinfo.COMP_ID and contractdeal.CONTRACT_ID = sellcontract.CONTRACT_ID ");
				sb.append(" and sellcontract.EARNEST_ID = earnestcontract.CONTRACT_ID and contractdeal2.CONTRACT_ID = earnestcontract.CONTRACT_ID ");
				sb.append(" and contractdeal2.STATUS = 2 and contractdeal.STATUS in(0 , 1) and enterprisequalify.BIZDISTRICT in " + districtList);
				if(sellerName != null && !"".equals(sellerName)){
					sb.append(" and sellerinfo.SELLER_NAME like ? ");
					params.add("%" + sellerName + "%");
				}
				if(compCode != null && !"".equals(compCode)){
					sb.append(" and enterprisequalify.comp_code =? ");
					params.add(compCode);
				}
				if(signDate1 != null && !"".equals(signDate1)){
					sb.append(" and contractdeal.SIGNDATE >=? ");
					String a = signDate1.replaceAll("-", "");
					a += "000000";
					params.add(a);
				}
				if(signDate2 != null && !"".equals(signDate2)){
					sb.append(" and contractdeal.SIGNDATE <=? ");
					String a = signDate2.replaceAll("-", "");
					a += "000000";
					params.add(a);
				}
				if(type != null && !"".equals(type)){
					sb.append(" and contractdeal.TYPE=? ");
					params.add(type);
				}
				if(confirmDate1 != null && !"".equals(confirmDate1)){
					sb.append(" and contractdeal.CONFIRMDATE >=? ");
					String a = confirmDate1.replaceAll("-", "");
					a += "000000";
					params.add(a);
				}
				if(confirmDate2 != null && !"".equals(confirmDate2)){
					sb.append(" and contractdeal.CONFIRMDATE <=? ");
					String a = confirmDate2.replaceAll("-", "");
					a += "000000";
					params.add(a);
				}
				if(sellerAddress != null && !"".equals(sellerAddress)){
					sb.append(" and sellerinfo.SELLER_ADDRESS like ? ");
					params.add("%" + sellerAddress + "%");
				}
				if(sellerCertcode != null && !"".equals(sellerCertcode)){
					sb.append(" and sellerinfo.SELLER_CERTCODE =? ");
					params.add(sellerCertcode);
				}
				if(sellerDlgCall != null && !"".equals(sellerDlgCall)){
					sb.append(" and sellerinfo.SELLER_DLG_CALL =? ");
					params.add(sellerDlgCall);
				}
				if(sellerDelegate != null && !"".equals(sellerDelegate)){
					sb.append(" and sellerinfo.SELLER_DELEGATE like ? ");
					params.add("%" + sellerDelegate + "%");
				}
				if(sellerProxy != null && !"".equals(sellerProxy)){
					sb.append(" and sellerinfo.SELLER_PROXY like ? ");
					params.add("%" + sellerProxy + "%");
				}
			}
			if(status != null && !"".equals(status) && !"-999".equals(status) && Integer.parseInt(status) != 8){
				sb.append(" select contractdeal.CONTRACT_ID,contractdeal.TYPE,contractdeal.SIGNDATE,to_char(contractdeal.STATUS) as STATUS, ");
				sb.append(" contractdeal.SIGNER,signer.LOGINNAME,contractdeal.SELLER_NAME,contractdeal.BUYER_NAME,contractdeal.location  ");
				sb.append(" from contractdeal, sellerinfo, signer,enterprisequalify ");
				sb.append(" where contractdeal.CONTRACT_ID = sellerinfo.CONTRACT_ID and contractdeal.SIGNER = signer.SIGNER_ID ");
				sb.append(" and enterprisequalify.COMP_ID = sellerinfo.COMP_ID and contractdeal.STATUS IN (1, 2, 4, 5, 7, 10, 11) ");
				sb.append(" and enterprisequalify.BIZDISTRICT in " + districtList);
				if(sellerName != null && !"".equals(sellerName)){
					sb.append(" and sellerinfo.SELLER_NAME like ? ");
					params.add("%" + sellerName + "%");
				}
				if(compCode != null && !"".equals(compCode)){
					sb.append(" and enterprisequalify.comp_code =? ");
					params.add(compCode);
				}
				if(signDate1 != null && !"".equals(signDate1)){
					sb.append(" and contractdeal.SIGNDATE >=? ");
					String a = signDate1.replaceAll("-", "");
					a += "000000";
					params.add(a);
				}
				if(signDate2 != null && !"".equals(signDate2)){
					sb.append(" and contractdeal.SIGNDATE <=? ");
					String a = signDate2.replaceAll("-", "");
					a += "000000";
					params.add(a);
				}
				if(type != null && !"".equals(type)){
					sb.append(" and contractdeal.TYPE=? ");
					params.add(type);
				}
				if(confirmDate1 != null && !"".equals(confirmDate1)){
					sb.append(" and contractdeal.CONFIRMDATE >=? ");
					String a = confirmDate1.replaceAll("-", "");
					a += "000000";
					params.add(a);
				}
				if(confirmDate2 != null && !"".equals(confirmDate2)){
					sb.append(" and contractdeal.CONFIRMDATE <=? ");
					String a = confirmDate2.replaceAll("-", "");
					a += "000000";
					params.add(a);
				}
				if(status != null && !"".equals(status) && !"-999".equals(status)){
					sb.append(" and contractdeal.STATUS = ? ");
					params.add(status);
				}
				if(sellerAddress != null && !"".equals(sellerAddress)){
					sb.append(" and sellerinfo.SELLER_ADDRESS like ? ");
					params.add("%" + sellerAddress + "%");
				}
				if(sellerCertcode != null && !"".equals(sellerCertcode)){
					sb.append(" and sellerinfo.SELLER_CERTCODE =? ");
					params.add(sellerCertcode);
				}
				if(sellerDlgCall != null && !"".equals(sellerDlgCall)){
					sb.append(" and sellerinfo.SELLER_DLG_CALL =? ");
					params.add(sellerDlgCall);
				}
				if(sellerDelegate != null && !"".equals(sellerDelegate)){
					sb.append(" and sellerinfo.SELLER_DELEGATE like ? ");
					params.add("%" + sellerDelegate + "%");
				}
				if(sellerProxy != null && !"".equals(sellerProxy)){
					sb.append(" and sellerinfo.SELLER_PROXY like ? ");
					params.add("%" + sellerProxy + "%");
				}
			}
			//System.out.println("sql=" + sb.toString());
			List list = DataBaseUtil.select(sb.toString(), params, ContractDealVO.class, page, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：通过合同ID查询合同甲方信息
	 * @param contractID
	 * @return
	 * @throws Exception
	 */
	public List<SellerInfoVO> searchSellerInfo(String contractID) throws Exception {
		try{
			List<Object> params = new ArrayList<Object>();
			StringBuffer sb = new StringBuffer();
			if(contractID != null && !"".equals(contractID)){
				sb.append(" select * from sellerinfo s where s.contract_id=? ");
				params.add(contractID);
			}
			List list = DataBaseUtil.select(sb.toString(), params, SellerInfoVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：通过合同ID查询合同乙方信息
	 * @param contractID
	 * @return
	 * @throws Exception
	 */
	public List<BuyerInfoVOFHE> searchBuyerInfoFHE(String contractID) throws Exception {
		try{
			List<Object> params = new ArrayList<Object>();
			StringBuffer sb = new StringBuffer();
			if(contractID != null && !"".equals(contractID)){
				sb.append(" select * from SS_CONTRACT_FHE_BUYER b where b.contract_id=? ");
				params.add(contractID);
			}
			System.out.println("sb.toString()= " + sb.toString() + " params=" + params + " ");
			List list = DataBaseUtil.select(sb.toString(), params, BuyerInfoVOFHE.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：通过合同ID查询合同乙方信息
	 * @param contractID
	 * @return
	 * @throws Exception
	 */
	public List<BuyerInfoVO> searchBuyerInfo(String contractID) throws Exception {
		try{
			List<Object> params = new ArrayList<Object>();
			StringBuffer sb = new StringBuffer();
			if(contractID != null && !"".equals(contractID)){
				sb.append(" select * from buyerinfo b where b.contract_id=? ");
				params.add(contractID);
			}
			List list = DataBaseUtil.select(sb.toString(), params, BuyerInfoVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}



	/**
	 * 功能描述：查询合同基本信息
	 * @param contractID
	 * @return
	 * @throws Exception
	 */
	public List<ContractDealVO> searchContractInfo(String contractID,int type) throws Exception {
		try{
			List<Object> params = new ArrayList<Object>();
			StringBuffer sb = new StringBuffer();
			if(contractID != null && !"".equals(contractID)){
				if(type == 1){
					sb.append(" select distinct presellcontract.N1_1 as DISTRICT, presellcontract.N2_1 as ROAD,presellcontract.N2_2 as ALLEY,presellcontract.N2_4 as BUILDING, ");
					sb.append(" presellcontract.BLDNUMTYPE as BLDNUMTYPE,presellcontract.N2_5 as FLOOR,presellcontract.LANE_NAME as LANE_NAME,presellcontract.SUB_LANE as SUB_LANE, ");
					sb.append(" presellcontract.N2_6 as ROOM_NUMBER,presellcontract.N3_3 as TOTAL_PRICE,presellcontract.N2_8 as BUILDING_AREA,presellcontract.PROJECT_NAME as BUILDNAME, ");
					sb.append(" presellcontract.N1_4 as LAND_AREA,presellcontract.HOUSETYPE as HOUSE_TYPE,presellcontract.N1_7 as BUILDING_STRUCTURE,presellcontract.N1_3 as LICENCE, ");
					sb.append(" presellcontract.N1_11 as LICENCE_PRESELL,sellerinfo.SELLER_NAME,sellerinfo.SERIAL as SELLER_SERIAL,sellerinfo.SELLER_DLG_CALL,sellerinfo.SELLER_BIZCARDCODE, ");
					sb.append(" buyerInfo.BUYER_NAME,buyerInfo.SERIAL as BUYER_SERIAL,buyerInfo.BUYER_CALL,buyerInfo.BUYER_CARDNAME, ");
					sb.append(" buyerInfo.BUYER_CARDCODE,contractdeal.CONTRACT_ID,contractdeal.CONFIRMDATE,contractdeal.SIGNDATE ");
					sb.append(" from contractdeal, presellcontract, buyerinfo, sellerinfo ");
					sb.append(" where contractdeal.CONTRACT_ID = presellcontract.CONTRACT_ID and contractdeal.CONTRACT_ID = buyerInfo.CONTRACT_ID ");
					sb.append(" and contractdeal.CONTRACT_ID = sellerinfo.CONTRACT_ID and contractdeal.CONTRACT_ID = ? ");
				}
				if(type == 2){
					sb.append(" SELECT DISTINCT sellcontract.N1_1 as DISTRICT,sellcontract.N2_1 as ROAD,sellcontract.N2_2 as ALLEY,sellcontract.N2_4 as BUILDING, ");
					sb.append(" sellcontract.BLDNUMTYPE as BLDNUMTYPE,sellcontract.N2_5 as FLOOR,sellcontract.LANE_NAME as LANE_NAME,sellcontract.SUB_LANE as SUB_LANE, ");
					sb.append(" sellcontract.N2_6 as ROOM_NUMBER,sellcontract.N3_3 as TOTAL_PRICE,sellcontract.N2_8 as BUILDING_AREA,sellcontract.PROJECT_NAME as BUILDNAME, ");
					sb.append(" sellcontract.HOUSETYPE as HOUSE_TYPE,sellcontract.N1_5 as LICENCE,sellerinfo.SELLER_NAME,sellerinfo.SERIAL as SELLER_SERIAL, ");
					sb.append(" sellerinfo.SELLER_DLG_CALL,sellerinfo.SELLER_BIZCARDCODE,buyerInfo.BUYER_NAME,buyerInfo.SERIAL as BUYER_SERIAL,buyerInfo.BUYER_CALL, ");
					sb.append(" buyerInfo.BUYER_CARDNAME,buyerInfo.BUYER_CARDCODE,contractdeal.CONTRACT_ID,contractdeal.CONFIRMDATE,contractdeal.SIGNDATE ");
					sb.append(" FROM contractdeal, sellcontract, buyerinfo, sellerinfo ");
					sb.append(" where contractdeal.CONTRACT_ID = sellcontract.CONTRACT_ID contractdeal.CONTRACT_ID = buyerInfo.CONTRACT_ID ");
					sb.append(" and contractdeal.CONTRACT_ID = sellerinfo.CONTRACT_ID and contractdeal.CONTRACT_ID = ? ");
				}
				if(type == 3){
					sb.append(" SELECT DISTINCT PROJECT.districtid as DISTRICT,earnestcontract.N1_1 as ROAD,earnestcontract.N1_2 as ALLEY,earnestcontract.N1_4 as BUILDING, ");
					sb.append(" earnestcontract.N1_5 as BLDNUMTYPE,earnestcontract.N1_6 as FLOOR,earnestcontract.LANE_NAME as LANE_NAME,earnestcontract.SUB_LANE as SUB_LANE, ");
					sb.append(" earnestcontract.N1_7 as ROOM_NUMBER,earnestcontract.N1_11 as BUILDING_AREA,earnestcontract.N2_3 as TOTAL_PRICE,earnestcontract.PROJECT_NAME as BUILDNAME, ");
					sb.append(" earnestcontract.N1_8 as LICENCE_TYPE,earnestcontract.N1_9 as LICENCE_NUMBER,sellerinfo.SELLER_NAME,sellerinfo.SERIAL as SELLER_SERIAL, ");
					sb.append(" sellerinfo.SELLER_DLG_CALL,sellerinfo.SELLER_BIZCARDCODE,buyerInfo.SERIAL as BUYER_SERIAL,buyerInfo.BUYER_NAME, ");
					sb.append(" buyerInfo.BUYER_CALL,buyerInfo.BUYER_CARDNAME,buyerInfo.BUYER_CARDCODE,contractdeal.CONTRACT_ID, ");
					sb.append(" contractdeal.CONFIRMDATE,contractdeal.SIGNDATE,contractdeal.STATUS ");
					sb.append(" FROM contractdeal, earnestcontract, buyerinfo, sellerinfo, PROJECT ");
					sb.append(" where contractdeal.CONTRACT_ID = earnestcontract.CONTRACT_ID and contractdeal.CONTRACT_ID = buyerInfo.CONTRACT_ID ");
					sb.append(" and contractdeal.CONTRACT_ID = sellerinfo.CONTRACT_ID and PROJECT.project_id=contractdeal.project_id ");
					sb.append(" and contractdeal.CONTRACT_ID = ? ");
				}
				sb.append(" order by sellerinfo.SERIAL desc,buyerInfo.SERIAL desc ");
				params.add(contractID);
			}
			//System.out.println("sql=" + sb.toString());
			List list = DataBaseUtil.select(sb.toString(), params, ContractDealVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}

	public List queryDeliverContract(Page page,String contractID,String seller,String buyer,String status,String confirmDateStart,String confirmDateEnd) throws Exception {
		try{
			List<Object> params = new ArrayList<Object>();
			StringBuffer sb = new StringBuffer();
			sb.append("  select c.contract_id as contractID,c.location as addr, ");
			sb.append("  c.seller as seller,c.buyer as buyer,c.deliver_id as deliverID, ");
			sb.append("  c.deliver_year||'-'||c.deliver_month||'-'||c.deliver_day as confirmdate  ");
			sb.append("  from (select d.deliver_year||''||d.deliver_month||''||d.deliver_day as deliverdate,d.* from delivercontract d) c ");
			sb.append("  where 1=1 ");
			if(contractID != null && !"".equals(contractID)){
				sb.append(" and c.contract_id =? ");
				params.add(contractID);
			}
			if(buyer != null && !"".equals(buyer)){
				sb.append(" and c.buyer like ? ");
				params.add("%" + buyer + "%");
			}
			if(status != null && !"-999".equals(status)){
				sb.append(" and c.status = ? ");
				params.add(status);
			}
			if(seller != null && !"".equals(seller)){
				sb.append(" and c.seller like ? ");
				params.add("%" + seller + "%");
			}
			if(confirmDateStart != null && !"".equals(confirmDateStart)){
				sb.append(" and c.deliverdate >=? ");
				params.add(confirmDateStart.replaceAll("-", ""));
			}
			if(confirmDateEnd != null && !"".equals(confirmDateEnd)){
				sb.append(" and c.deliverdate <=? ");
				params.add(confirmDateEnd.replaceAll("-", ""));
			}
			sb.append(" order by c.upddate desc,c.updtime desc ");
			List list = DataBaseUtil.select(sb.toString(), params, DeliverContractVO.class, page, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}
	}

	/**
	 * 功能描述：查询合同主模板列表
	 * @param compID
	 * @param legalManCode
	 * @param projectName
	 * @param compName
	 * @param startDate
	 * @param endDate
	 * @param startID
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<SellTemplateVO> queryTemplateList(String compCode,String legalManCode,String projectName,String compName,String startDate,String endDate,String startCode,String district,Page page) throws Exception {
		try{

			StringBuffer sb = new StringBuffer();
			List<Object> params = new ArrayList<Object>();
			sb.append(" SELECT  DISTINCT  project.projectname,start_unit.start_code,CONTRACTTEMPLATE_CS.TEMPNAME,CONTRACTTEMPLATE_CS.TEMPLATEID,CONTRACTTEMPLATE_CS.updDate,CONTRACTTEMPLATE_CS.updPerson,'2' as TYPE,'2' as typename ");
			sb.append(" FROM enterprisequalify,CONTRACTTEMPLATE_CS,sellertemplate,project,start_unit ");
			sb.append(" where CONTRACTTEMPLATE_CS.TEMPLATEID= sellertemplate.TEMPLATE_ID and project.PROJECT_ID = CONTRACTTEMPLATE_CS.PROJECTID and CONTRACTTEMPLATE_CS.STARTID = start_unit.START_ID and enterprisequalify.COMP_ID = sellertemplate.COMP_ID ");
			sb.append(" and project.DISTRICTID in " + district);
			if(legalManCode != null && !"".equals(legalManCode)){
				sb.append(" and enterprisequalify.LEGALMANCODE = ? ");
				params.add(legalManCode);
			}
			if(projectName != null && !"".equals(projectName)){
				sb.append(" and project.PROJECTNAME like ? ");
				params.add("%" + projectName + "%");
			}
			if(compName != null && !"".equals(compName)){
				sb.append(" and enterprisequalify.NAME like ? ");
				params.add("%" + compName + "%");
			}
			if(startDate != null && !"".equals(startDate)){
				sb.append(" and CONTRACTTEMPLATE_CS.CREDATE >=? ");
				params.add(startDate.replace("-", ""));
			}
			if(endDate != null && !"".equals(endDate)){
				sb.append(" and CONTRACTTEMPLATE_CS.CREDATE <=? ");
				params.add(endDate.replace("-", ""));
			}
			if(startCode != null && !"".equals(startCode)){
				sb.append(" and start_unit.START_CODE = ? ");
				params.add(startCode);
			}
			if(compCode != null && !"".equals(compCode)){
				sb.append(" and enterprisequalify.COMP_CODE = ? ");
				params.add(compCode);
			}
			sb.append(" UNION ALL ");
			sb.append(" SELECT  DISTINCT  project.projectname,start_unit.start_code,CONTRACTTEMPLATE_YS.TEMPNAME,CONTRACTTEMPLATE_YS.TEMPLATEID,CONTRACTTEMPLATE_YS.updDate,CONTRACTTEMPLATE_YS.updPerson,'1' as TYPE,'1' as typename ");
			sb.append(" FROM enterprisequalify,CONTRACTTEMPLATE_YS,sellertemplate,project,start_unit ");
			sb.append(" where CONTRACTTEMPLATE_YS.TEMPLATEID = sellertemplate.TEMPLATE_ID and project.PROJECT_ID = CONTRACTTEMPLATE_YS.PROJECTID  and CONTRACTTEMPLATE_YS.STARTID = start_unit.START_ID  and enterprisequalify.COMP_ID = sellertemplate.COMP_ID ");
			sb.append(" and project.DISTRICTID in " + district);
			if(legalManCode != null && !"".equals(legalManCode)){
				sb.append(" and enterprisequalify.LEGALMANCODE = ? ");
				params.add(legalManCode);
			}
			if(projectName != null && !"".equals(projectName)){
				sb.append(" and project.PROJECTNAME like ? ");
				params.add("%" + projectName + "%");
			}
			if(compName != null && !"".equals(compName)){
				sb.append(" and enterprisequalify.NAME like ? ");
				params.add("%" + compName + "%");
			}
			if(startDate != null && !"".equals(startDate)){
				sb.append(" and CONTRACTTEMPLATE_YS.CREDATE >=? ");
				params.add(startDate.replace("-", ""));
			}
			if(endDate != null && !"".equals(endDate)){
				sb.append(" and CONTRACTTEMPLATE_YS.CREDATE <=? ");
				params.add(endDate.replace("-", ""));
			}
			if(startCode != null && !"".equals(startCode)){
				sb.append(" and start_unit.START_CODE = ? ");
				params.add(startCode);
			}
			if(compCode != null && !"".equals(compCode)){
				sb.append(" and enterprisequalify.COMP_CODE = ? ");
				params.add(compCode);
			}
			//System.out.println(sb.toString());
			List list = DataBaseUtil.select(sb.toString(), params, SellTemplateVO.class, page, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{

		}
	}

	/**
	 * 功能描述：通过合同ID查询合同乙方信息
	 * @param contractID
	 * @return
	 * @throws Exception
	 */
	public List<BuyerInfoVO> searchBuyerInfoOrderSerial(String contractID,String signFlag) throws Exception {
		try{
			List<Object> params = new ArrayList<Object>();
			StringBuffer sb = new StringBuffer();
			if(contractID != null && !"".equals(contractID)){
				sb.append(" select * from buyerinfo  where contract_id = ? ");
				if(signFlag != null && !"".equals(signFlag)){
					if("0".equals(signFlag)){
                        sb.append(" and (signflag = " + signFlag + " or signflag is null )");
                    }else{
                        sb.append(" and signflag = " + signFlag);
                    }
				}
				sb.append(" order by serial asc");
				params.add(contractID);
			}
			List list = DataBaseUtil.select(sb.toString(), params, BuyerInfoVO.class, conn);
			return list;
		}catch (Exception e){
			logger.error(e);
			throw e;
		}finally{
			
		}
	}


}
