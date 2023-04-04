package com.hybirdapp.sample.cmmn;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DataClass implements Serializable {

	private HashMap variables;
	protected Log log = LogFactory.getLog(this.getClass());

	public DataClass() {
		this.variables = new HashMap(20);
	}

	public HashMap getMap() {
		return this.variables;
	}


	public HashMap setMapNReturn(HashMap map) {
		this.variables = map;

		if(variables == null) this.variables = new HashMap(20);
		return this.variables;
	}

	public void setMap(HashMap map) {
		this.variables = map;

		if(variables == null) this.variables = new HashMap(20);
	}

	public DataClass(ServletRequest req) {
		this.variables = new HashMap(20);
		setValues(req);
	}

	public DataClass(ResultSet rs){
		this.variables = new HashMap();
		this.setValues(rs);
	}


//	public int getStartNo(){
//		return (this.CurrPage - 1) * this.ListSize + 1;
//	}
//
//	public int getEndNo() {
//		return this.CurrPage * this.ListSize;
//	}

//	public int getListStartNo(){
//		return this.TotalCount - (this.CurrPage - 1) * this.ListSize;
//	}

	public int getSize() {
		if(variables != null) {
			return variables.size();
		} else {
			return 0;
		}
	}

	public String getTag(String name) {
		return getTag(name, "");
	}
	public String getTag(String name, String tab) {
		return tab + "<"+name+"><![CDATA[" + getString(name) + "]]></"+name+">\n";
	}

	public Object getObject(String name) {
		return variables.get(name);
	}

	public String get(String name) {
		return getString(name);
	}
	public String getString(String name) {
		String val = null;
		val = nullCheck(""+variables.get(name));
		return val;
	}

	public int getInt(String name) {
		String val = getString(name);
		if("".equals(val)) val = "0";
		val = getCommaNum(val, "#");
		return Integer.parseInt(val);
	}
	
	public static String getCommaNum(String str, String form) {
		String rtn = "";
		if(str == null || "".equals(str)) {
			rtn = "";
		} else {
			try {
				java.text.NumberFormat nf = java.text.NumberFormat.getNumberInstance();
				java.text.DecimalFormat df = (java.text.DecimalFormat)nf ;
				df.applyPattern(form);
				rtn = df.format(Double.valueOf(str)) ;
			} catch(Exception e) {
				//rtn = "";
				//출력물 스트링 반환
				rtn = str;
			}
		}
		return rtn;
	}

	public double getDouble(String name) {
		String val = "0"+getString(name);
		if("".equals(val)) val = "0";
		return Double.parseDouble(val);
	}


	public String getHtmlString(String name){
		String val = null;
	    val = nullCheck((String)this.variables.get(name));
	    val = val.replaceAll("\"", "&quot;");
	    val = val.replaceAll("'", "&#39;");
	    return val;
	  }

	/***************************** Set Methods **********************************/
	public void set(String name, Object obj) {
		setObject(name, obj);
	}
	public void setObject(String name, Object obj) {
		variables.put(name, obj);
	}

	public void set(String name, String val) {
		setString(name, val);
	}
	public void setString(String name, String val) {
		variables.put(name, nullCheck(val));
	}

	public void setValues(Hashtable table) {
		Enumeration e = table.keys();

		String name = null;
		while(e.hasMoreElements()) {
			name = (String)e.nextElement();
			setObject(name, table.get(name));
		}
	}

	public void setValues(ResultSet rs) {
		try {
	        ResultSetMetaData rsmeta = rs.getMetaData();
	        int col_cnt = rsmeta.getColumnCount();

			String name = null;
			String val = null;

			StringTokenizer st = null;
			StringBuffer tmp = null;
			String token = null;
	        for(int i=0; i<col_cnt; i++) {
				name = rsmeta.getColumnName(i+1).toLowerCase();
				val = rs.getString(name);

				variables.put(name, nullCheck(val));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void setValues(ServletRequest req) {
		String addr = req.getRemoteAddr();
		String name ;
		Object value ;
		try {
			variables.put("REQUEST_URL", req.getServerName());
			variables.put("ACCESS_IP", addr);
			HttpSession session = ((HttpServletRequest)req).getSession(true);
			String sessionId = session.getId();
			variables.put("SESSION_ID", sessionId); // SESSION Id

			String[] arrValues = null;
			int arrLen = 0;
			for (Enumeration e = req.getParameterNames() ; e.hasMoreElements() ;) {
				name = (String)e.nextElement() ;
				value= req.getParameterValues( name );
				value= chkData( value );

				if(value.getClass().isArray()) {
					arrLen = ((String [])value).length;
					arrValues = new String[arrLen];
					for(int i =0 ; i < ((String []) value).length ; i++) {
						arrValues[i] = ((String[]) value)[i];
					}


					variables.put( name, arrValues) ;
				} else {
					value = ((String)value).trim() ;

					//파라미터 관련된 문제=======================
//					value = value.toString();
					value = cleanXSS(value.toString());
					//=======================================


					variables.put( name, value) ;
				}
			}
		} catch(Exception e) {
			log.error("setValues() Exception : " + e.toString());
			variables = new HashMap(20);
		}
	}


	public void setValues(HttpServletRequest req) {
		String addr = req.getRemoteAddr();
		String name ;
		Object value ;
		try {
			variables.put("REQUEST_URL", req.getServerName());
			variables.put("ACCESS_IP", addr);
			HttpSession session = req.getSession(true);
			String sessionId = session.getId();
			variables.put("SESSION_ID", sessionId); // SESSION Id

			String[] arrValues = null;
			int arrLen = 0;
			for (Enumeration e = req.getParameterNames() ; e.hasMoreElements() ;) {
				name = (String)e.nextElement() ;
				value= req.getParameterValues( name );
				value= chkData( value );

				if(value.getClass().isArray()) {
					arrLen = ((String [])value).length;
					arrValues = new String[arrLen];
					for(int i =0 ; i < ((String []) value).length ; i++) {
						arrValues[i] = ((String[]) value)[i];
						log.debug("setValues() [" + name + "] [" + i + "]:" + arrValues[i] );
					}
					variables.put( name, arrValues) ;
					log.debug("setValues() [" + name + "] is Array with length " + arrLen );
				} else {
					value = ((String)value).trim() ;
					value = value.toString();
					variables.put( name, value) ;
					log.debug("setValues() [" + name + "]:" + value );
				}
			}
		} catch(Exception e) {
			log.error("setValues() Exception : " + e.toString());
			variables = new HashMap(20);
		}
	}

	public Object chkData(Object org) {
		if(org == null) return null ;
		if( org.getClass().isArray() &&	((Object[]) org).length <= 1) {
			return ((Object[]) org)[0] ;
		} else {
			return org ;
		}
	}


	public String toXML(String tab) {

        StringBuffer buff = new StringBuffer();
		String name = null;
		String val = null;

		Iterator it = variables.keySet().iterator();
		ArrayList arr = new ArrayList();
		while(it.hasNext()) {
			arr.add(it.next());
		}
		Collections.sort(arr);

		int size = arr.size();
		Object o = null;
		for(int i=0; i<size; i++) {
			name = (String)arr.get(i);
			o = variables.get(name);
			if( !java.util.ArrayList.class.equals( o.getClass()) ){
				val = get(name);
				buff.append(tab + "<" + name + "><![CDATA[" + val + "]]></" + name + ">\n");
			}
		}
		return buff.toString();
	}

	public String toXML(){
		return toXML("");
	}


	public String nullCheck(String str) {
		if(str == null || "null".equals(str)) {
			str = "";
		}
		return str.trim();
	}

	public DataClass getKeyDataClass(){
	    Set dcSet = this.variables.keySet();
        Iterator it = dcSet.iterator();
        String key = "";
        DataClass dc = new DataClass();
        while(it.hasNext()){
            key = (String)it.next();
            dc.set(key, "");
        }
        return dc;
	}

	public String[] getArrayString(String name){
	    String[] rtnArr = null;

	    Object obj = getObject(name);

	    if (obj != null) {
	      if (obj.getClass().isArray()) {
	        rtnArr = (String[])obj;
	      } else {
	        rtnArr = new String[1];
	        rtnArr[0] = ((String)obj);
	      }
	      this.log.debug("getArrayString("+name+") made array length : " + rtnArr.length);
	    }
	    return rtnArr;
	  }


	public void remove(String name) {
        variables.remove(name);
    }

    public String[] getKeyArray(){
        Set dcSet = this.variables.keySet();
        return (String[])dcSet.toArray(new String[dcSet.size()]);
    }


	public String getQryString() {
        StringBuffer buff = new StringBuffer();
		String name = null;
		String val = null;

		Iterator it = variables.keySet().iterator();
		ArrayList arr = new ArrayList();
		while(it.hasNext()) {
			arr.add(it.next());
		}
		Collections.sort(arr);

		int size = arr.size();
		Object o = null;
		for(int i=0; i<size; i++) {
			if(i==0){
				buff.append("?");
			}else{
				buff.append("&");
			}
			name = (String)arr.get(i);
			o = variables.get(name);
			if( !java.util.ArrayList.class.equals( o.getClass()) ){
				val = get(name);
				buff.append(name+"="+val);
			}
		}
		return buff.toString();
	}

	//xss공격 관련하여 파라미터 필터링을 한다.
	public String cleanXSS(String value) {
//		String value = paramStr.toLowerCase();

	 //   value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	 //   value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
	 //   value = value.replaceAll("'", "& #39;");
	    value = value.replaceAll("eval\\((.*)\\)", "");
	    value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
	    value = value.replaceAll("script", "");
	    value = value.replaceAll("onerror", "");
	    //value = value.replaceAll("img", "");
	    return value;
	}


}
