/**
 * <p>Title: Nankang Framework 1.0</p>
 * <p>Description: 南康J2EE框架</p>
 * <p>Copyright: Copyright (c) 2005 上海南康科技有限公司</p>
 * <p>Company: 上海南康科技有限公司</p>
 * <p>修改记录:</p>
 * <p>2005-10-10 崔晓东 增加了对XML解析的支持。必须在JDK 1.4以上运行。</p>
 * 
 * @author 崔晓东
 * @version 1.0  
 */

package com.netcom.nkestate.framework.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * XmlUtil - 提供一组XML操作的静态方法.
 * 
 * @author 崔晓东
 * @version 1.0
 * @since nkframework 0.5
 * @since jdk 1.4
 */
public class XmlUtil {
	
	
	public static String replaceString(String text, String repl, String with)
    {
        return replaceString(text, repl, with, -1);
    }

    public static String replaceString(String text, String repl, String with, int max)
    {
        if(text == null)
            return null;
        StringBuffer buffer = new StringBuffer(text.length());
        int start = 0;
        for(int end = 0; (end = text.indexOf(repl, start)) != -1;)
        {
            buffer.append(text.substring(start, end)).append(with);
            start = end + repl.length();
            if(--max == 0)
                break;
        }

        buffer.append(text.substring(start));
        return buffer.toString();
    }
	
	public static String escapeXml(String str)
    {
        str = XmlUtil.replaceString(str, "&", "&amp;");
        str = XmlUtil.replaceString(str, "<", "&lt;");
        str = XmlUtil.replaceString(str, ">", "&gt;");
        str = XmlUtil.replaceString(str, "\"", "&quot;");
        str = XmlUtil.replaceString(str, "'", "&apos;");
        return str;
    }

    public static String unescapeXml(String str)
    {
        str = XmlUtil.replaceString(str, "&amp;", "&");
        str = XmlUtil.replaceString(str, "&lt;", "<");
        str = XmlUtil.replaceString(str, "&gt;", ">");
        str = XmlUtil.replaceString(str, "&quot;", "\"");
        str = XmlUtil.replaceString(str, "&apos;", "'");
        return str;
    }
	
	/**
	 * 解析一个XML数据流，返回其Document文档。
	 * 
	 * @param in
	 *            XML数据的输入流。
	 * @param encoding
	 *            该数据流中使用的编码，比如"GBK".
	 * @return Document - XML Document对象，如果解析失败，返回null。
	 */
	public static Document parseXml(InputStream in, String encoding) {
		try {
			Charset charset = Charset.forName(encoding); //  在此处声明GBK方式
			Reader r = new InputStreamReader(in, charset);
			InputSource source = new InputSource(r);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(source);
			return doc;
		} catch (IOException e) {
			System.out.println(e);
		} catch (ParserConfigurationException e) {
			System.out.println(e);
		} catch (SAXException e) {
			System.out.println(e);
		}
		return null;
	}

	/**
	 * 根据标记名称取得子节点列表。
	 * 
	 * @param me
	 *            选定的节点。
	 * @param tag_name
	 *            该节点下的标记名称。
	 * @return List - 节点列表，元素为Node。如果没有符合条件的节点，返回空List而不是null.
	 */
	public static List getSubNodesByTagName(Node me, String tag_name) {
		List list = new ArrayList();
		if (me != null) {
			NodeList childs = me.getChildNodes();
			for (int i = 0; i < childs.getLength(); i++) {
				Node node = childs.item(i);
				if (node.getNodeName().equalsIgnoreCase(tag_name)) {
					list.add(node);
				}
			}
		}
		return list;
	}

	/**
	 * 根据标记名称取得一个子节点。
	 * 
	 * @param me
	 *            选定的节点。
	 * @param tag_name
	 *            标记名称。
	 * @return Node - 符合条件的节点。如果不存在，返回null.
	 */
	public static Node getSubNodeByTagName(Node me, String tag_name) {
		if (me != null) {
			NodeList childs = me.getChildNodes();
			for (int i = 0; i < childs.getLength(); i++) {
				Node node = childs.item(i);
				if (node.getNodeName().equalsIgnoreCase(tag_name)) {
					return node;
				}
			}
		}
		return null;
	}

	/**
	 * 根据标记名称返回子节点的值。
	 * 
	 * @param me
	 *            选定的节点。
	 * @param tag_name
	 *            标记名称。
	 * @return String - 节点值。如果节点不存在，返回null。
	 */
	public static String getSubNodeValueByTagName(Node me, String tag_name)
    {
        Node node = getSubNodeByTagName(me, tag_name);
        if(node != null)
        {
            Node fnode = node.getFirstChild();
            if(fnode != null)
                return fnode.getNodeValue();
        }
        return null;
    }
	
	public static String getNodeValue(Node me)
    {
        if(me != null)
        {
            Node fnode = me.getFirstChild();
            if(fnode != null)
                return fnode.getNodeValue();
        }
        return null;
    }

	/**
	 * 返回节点的属性值。
	 * 
	 * @param node
	 *            指定的节点。
	 * @param attribute_name
	 *            属性名称。
	 * @return String - 属性值。如果节点不存在，或者属性不存在，返回null.
	 */
	public static String getNodeAttributeValue(Node node, String attribute_name) {
		if (node != null) {
			NamedNodeMap attributes = node.getAttributes();
			if (attributes != null) {
				Node attr = attributes.getNamedItem(attribute_name);
				if(attr!=null) {
					return attr.getNodeValue();
				}
			}
		}
		return null;
	}
	
	/**
	 * 设置节点的属性值。
	 * 
	 * @param node
	 *            指定的节点。
	 * @param attribute_name
	 *            属性名称。
	 * @param attribute_value
	 *            属性名称。
	 * @return 
	 */
	public static boolean setNodeAttributeValue(Node node, String attribute_name,String attribute_value) {
		boolean result = false;
	    if (node != null) {
			NamedNodeMap attributes = node.getAttributes();
			if (attributes != null) {
				Node attr = attributes.getNamedItem(attribute_name);
				if(attr!=null) {
					attr.setNodeValue(attribute_value);
				} else {
				    //Node n = attributes.setNamedItem(node);
				    //n.setNodeValue(attribute_value);
				}
			}
			result = true;
		}
		return result;
	}
}