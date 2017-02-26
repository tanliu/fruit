package com.fruit.utils;

import javax.servlet.http.HttpServletRequest;



/**
 * 分页组件
 *
 * @eric
 */
public class PageBean {
    /**
     * 当前页
     */
    private int curPage;
    /**
     * 当前页附近多少页，例如当前为第10页，nearCount为5，那返回： 8，9，10，11，12
     */
    private int[] nearby;
    /**
     * 最大页
     */
    private int maxPage;
    /**
     * 每页多少个
     */
    private int perPage;
    /**
     * 总共多少条数据
     */
    private long total;
    private boolean needMinPage = true;
    private boolean needMaxPage = true;
    private boolean needMinPageRoad = true;
    private boolean needMaxPageRoad = true;
    private String uriAndQuery=null;
   
    /**
     * @param curPage
     * @param maxPage
     * @param nearCount
     * @param perPage   每页多少个
     */
    private PageBean(Integer curPage, int nearCount, int perPage, Integer total,HttpServletRequest request) {
    	if(total==null||total<0) total=0;
    	if (perPage < 1) {
            perPage = 10;
        }
        int maxPage = PageBean.getMaxPage(total, perPage);
        if (curPage == null || curPage < 1) {
            curPage = 1;
        }
        if (curPage > maxPage) {
            curPage = maxPage;
        }
        this.curPage = curPage;
        this.maxPage = maxPage;
        this.perPage = perPage;
        this.total = total;
        nearby = new int[nearCount];
        String query = request.getQueryString();
        if(query==null||"".equals(query)){
        	query="page=1";
        }else{
        	String pageString = request.getParameter("page");
        	if(pageString==null) query=query+"&page=1";
        }
        String temp =request.getRequestURI()+"?"+query;
        this.uriAndQuery=temp.replaceFirst("page=\\d+", "page=pageNum");
        this.setNearby();
    }

    /**
     * 默认获取当前页附近的10页
     *
     * @param curPage 当前页
     * @param total   总数
     * @param perPage 每页多少条
     * @return
     */
    public static PageBean getInstance(Integer curPage, Integer total, int perPage,HttpServletRequest request) {
        return new PageBean(curPage, 5, perPage, total, request);
    }

    /**
     * @param curPage   当前页
     * @param total     总数
     * @param perPage   每页多少条
     * @param nearCount 当前页附近多少页，例如当前为第10页，nearCount为5，那返回： 8，9，10，11，12
     * @return
     */
    public static PageBean getInstance(Integer curPage, Integer total, int perPage, int nearCount,HttpServletRequest request) {
        return new PageBean(curPage, nearCount, perPage, total,request);
    }

    /**
     * 默认每页10个,获取附近的10页
     *
     * @param curPage 当前页
     * @param total   总数
     * @return
     */
    public static PageBean getInstance(Integer curPage, Integer total,HttpServletRequest request) {
        return new PageBean(curPage, 5, 10, total,request);
    }

    public static int getMaxPage(long total, int perPage) {
        float tmpF = (float) total / perPage;
        int tmpI = (int) tmpF;
        if (tmpF > tmpI) {
            return tmpI + 1;
        }
        return tmpI;
    }

    public static int formart(Integer page) {
        if (page == null || page < 1) {
            page = 1;
        }
        return page;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    public int getStartNum() {
        return (this.curPage - 1) * this.perPage;
    }

    public int[] getNearby() {
        return nearby;
    }

    private void setNearby() {
        int curIndex = nearby.length / 2;
        if (curPage == maxPage) {
            needMaxPage = false;
            needMaxPageRoad = false;
        }
        for (int i = curIndex, j = 0; i >= 0 && curPage - j >= 1; i--, j++) {
            nearby[i] = curPage - j;
            if (nearby[i] == 1) {
                needMinPage = false;
                needMinPageRoad = false;
            }
        }
        for (int i = curIndex + 1, j = 1; i < nearby.length && curPage + j <= maxPage; i++, j++) {
            nearby[i] = curPage + j;
            if (nearby[i] == maxPage) {
                needMaxPage = false;
                needMaxPageRoad = false;
            }
        }

        if (nearby[0] == 2) {
            this.needMinPageRoad = false;
        }
        int nearBySize = nearby.length;

        if (nearby[nearBySize - 1] == (this.getMaxPage() - 1)) {
            this.needMaxPageRoad = false;
        }
    }

    

    public boolean isNeedMinPage() {
		return needMinPage;
	}



	public boolean isNeedMaxPage() {
		return needMaxPage;
	}



	public boolean isNeedMinPageRoad() {
		return needMinPageRoad;
	}



	public boolean isNeedMaxPageRoad() {
		return needMaxPageRoad;
	}



	public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

	public String getUriAndQuery() {
		return uriAndQuery;
	}

	public void setUriAndQuery(String uriAndQuery) {
		this.uriAndQuery = uriAndQuery;
	}

}
