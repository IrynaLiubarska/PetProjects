//package com.liubarska.db.delete;
//
//import static com.liubarska.db.delete.DeletePolicy.*;
//
///**
// * Created by Iryna on 09.07.2018.
// */
//public class DeleteStrategyFactory {
//
//    public static DeleteStrategy create(DeletePolicy deletePolicy) {
//        if (DELETE_RESTRICT.equals(deletePolicy)) {
//            return new DeleteRestrict();
//        } else if (DELETE_NO_ACTION.equals(deletePolicy)) {
//            return new DeleteNoAction();
//        } else if (DELETE_CASCADE.equals(deletePolicy)) {
//            return new DeleteCascade();
//        } else {
//            throw new IllegalStateException("Unknown delete policy");
//        }
//    }
//}
