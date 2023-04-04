package com.hybirdapp.sample.mngr.fclMng.service;

import java.util.HashMap;
import java.util.Map;

public class ContentMapGenerator {
    /* 시설등록 */
    public static Map<String, String> makeFcltInsert(final Map<String, String> params, final String mngrId, final String remoteIp) {
        if (params == null) {
            return null;
        }

        final String[] SRC_MAP_ARRAY = {"fcltNm", "slCmt", "limitDesc", "fcltClass"};
        Map<String, String> map = new HashMap<>();
        for (int i = 0, size = SRC_MAP_ARRAY.length; i < size; i++) {
            map.put(SRC_MAP_ARRAY[i], params.get(SRC_MAP_ARRAY[i]));
        }
        map.put("isrtId", mngrId);
        map.put("isrtIp", remoteIp);

        return map;
    }
    /* 시설등록 */
    public static Map<String, String> makeFcltUpdate(final Map<String, String> params, final String mngrId, final String remoteIp) {
        if (params == null) {
            return null;
        }

        final String[] SRC_MAP_ARRAY = {"fcltSeq", "fcltNm", "slCmt", "limitDesc", "fcltClass"};
        Map<String, String> map = new HashMap<>();
        for (int i = 0, size = SRC_MAP_ARRAY.length; i < size; i++) {
            map.put(SRC_MAP_ARRAY[i], params.get(SRC_MAP_ARRAY[i]));
        }
        map.put("updtId", mngrId);
        map.put("updtIp", remoteIp);

        return map;
    }
    /* P_PLACE 등록 */
    public static Map<String, String> makePlaceInsert(final Map<String, String> params, final String mngrId, final String remoteIp) {
        if (params == null) {
            return null;
        }

        Map<String, String> map = new HashMap<>();
        map.put("plDesc", params.get("fcltNm"));
        map.put("plRemark", params.get("slCmt"));
        map.put("isrtId", mngrId);
        map.put("isrtIp", remoteIp);

        return map;
    }
    /* P_PLACE 등록 */
    public static Map<String, String> makePlaceUpdate(final Map<String, String> params, final String mngrId, final String remoteIp) {
        if (params == null) {
            return null;
        }

        Map<String, String> map = new HashMap<>();
        map.put("plDesc", params.get("fcltNm"));
        map.put("plRemark", params.get("slCmt"));
        map.put("updtId", mngrId);
        map.put("updtIp", remoteIp);

        return map;
    }

    /* 패키지상품등록 */
    public static Map<String, String> makePkgPrdInsert(final Map<String, String> params, final String mngrId, final String remoteIp) {
        if (params == null) {
            return null;
        }

        final String[] SRC_MAP_ARRAY = {"fcltNm", "slCmt", "limitDesc", "fcltClass", "muOnoffKb", "muAgentCd"};
        Map<String, String> map = new HashMap<>();
        for (int i = 0, size = SRC_MAP_ARRAY.length; i < size; i++) {
            map.put(SRC_MAP_ARRAY[i], params.get(SRC_MAP_ARRAY[i]));
        }
        map.put("isrtId", mngrId);
        map.put("isrtIp", remoteIp);

        return map;
    }

    /* 패키지상품 수정 */
    public static Map<String, String> makePkgPrdUpdate(final Map<String, String> params, final String mngrId, final String remoteIp) {
        if (params == null) {
            return null;
        }

        final String[] SRC_MAP_ARRAY = {"fcltSeq", "fcltNm", "slCmt", "limitDesc", "fcltClass", "muOnoffKb", "muAgentCd"};
        Map<String, String> map = new HashMap<>();
        for (int i = 0, size = SRC_MAP_ARRAY.length; i < size; i++) {
            map.put(SRC_MAP_ARRAY[i], params.get(SRC_MAP_ARRAY[i]));
        }
        map.put("updtId", mngrId);
        map.put("updtIp", remoteIp);

        return map;
    }
}
