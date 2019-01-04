/*
 * Copyright (C) 2009-2016 Hangzhou 2Dfire Technology Co., Ltd. All rights reserved
 */
package com.joseph.provider.common.logger;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * PSLoggerMarkers
 *
 * @author zhutou
 * @since 2018-05-28
 */
public class PSLoggerMarkers {

    /**
     * 超时警告
     */
    public static final Marker TIME_OUT_WARM = MarkerFactory.getMarker("time_out");
    /**
     * 异常处理
     */
    public static final Marker EXCEPTION_HANDLER = MarkerFactory.getMarker("exception_handler");
    /**
     * 业务marker
     */
    public static final Marker BUSINESS = MarkerFactory.getMarker("business");

    /**
     * 数据埋点
     */
    public static final Marker STATISTIC = MarkerFactory.getMarker("statistic");

    /**
     * solr marker
     */
    public static final Marker SOLR = MarkerFactory.getMarker("solr");

    /**
     * solr店铺 marker
     */
    public static final Marker SOLR_SHOP = MarkerFactory.getMarker("solr_shop");

    /**
     * solr折扣 marker
     */
    public static final Marker SOLR_DISCOUNT = MarkerFactory.getMarker("solr_discount");

    /**
     * 定时任务
     */
    public static final Marker JOB = MarkerFactory.getMarker("job");

    /**
     * 确认支付
     */
    public static final Marker GUARANTEE_PAYMENT = MarkerFactory.getMarker("guarantee_payment");

    /**
     * 订单核销
     */
    public static final Marker TRADE_VERIFY = MarkerFactory.getMarker("trade_verify");

    /**
     * 订单退款
     */
    public static final Marker TRADE_REFUND = MarkerFactory.getMarker("refund_refund");

    /**
     * mq消息marker
     */
    public static final Marker ROCKET_MQ = MarkerFactory.getMarker("rocket_mq");
    /**
     * 店铺
     */
    public static final Marker SHOP = MarkerFactory.getMarker("shop");
    /**
     * 店铺列表
     */
    public static final Marker PRESLL_SHOP = MarkerFactory.getMarker("presell_shop");

    /**
     * 风控
     */
    public static final Marker COUNSELLOR = MarkerFactory.getMarker("counsellor");

    /**
     * 砍价
     */
    public static final Marker CUT_PRICE = MarkerFactory.getMarker("cut_price");
    /**
     * 线程池marker
     */
    public static final Marker THREAD_POOL = MarkerFactory.getMarker("threadPool");
    /**
     *
     */
    public static final Marker LOCK_STOCK = MarkerFactory.getMarker("lockStock");
    /**
     * 堂食购物车
     */
    public static final Marker PRESELL_CART = MarkerFactory.getMarker("presellCart");
    /**
     * 智能点餐
     */
    public static final Marker INTELLIGENCE = MarkerFactory.getMarker("intelligence");

    /**
     * 异步任务
     */
    public static final Marker ASYAN_TASK = MarkerFactory.getMarker("asyanTask");
    /**
     * 订单
     */
    public static final Marker ORDER = MarkerFactory.getMarker("asyanTask");
    /**
     * 云购物车
     */
    public static final Marker CLOUD_CART = MarkerFactory.getMarker("CloudCart");
    public static final Marker FORCE_MENU = MarkerFactory.getMarker("forceMenu");
    @Deprecated
    public static final Marker CART = MarkerFactory.getMarker("Cart");

    public static final Marker MULTIPLE_CART = MarkerFactory.getMarker("multipleCart");
}
