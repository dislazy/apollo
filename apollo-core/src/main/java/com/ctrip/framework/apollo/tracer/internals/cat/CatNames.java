package com.ctrip.framework.apollo.tracer.internals.cat;

/**
 * @author Jason Song(song_s@ctrip.com)
 */
public interface CatNames {
  String CAT_CLASS = "com.alidaodao.cat.Cat";
  String LOG_ERROR_METHOD = "logError";
  String LOG_EVENT_METHOD = "logEvent";
  String NEW_TRANSACTION_METHOD = "newTransaction";

  String CAT_TRANSACTION_CLASS = "com.alidaodao.cat.message.Transaction";
  String SET_STATUS_METHOD = "setStatus";
  String ADD_DATA_METHOD = "addData";
  String COMPLETE_METHOD = "complete";
}
