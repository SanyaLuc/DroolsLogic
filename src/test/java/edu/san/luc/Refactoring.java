//package edu.san.luc;
//
///**
// * Created by sanya on 18.05.15.
// */
//public class Refactoring {
//
//    public static class EasyFormsUtils {
//
//        public static boolean hasCorrectFieldsets(Form form) {
//            boolean result = false;
//
//            result = applyChecks(form);
//
//            return result;
//        }
//
//        private static boolean applyChecks(Form form) {
//            boolean result = true;
//            boolean mustStart = true;
//            int elementBetweenFieldSetTag = -1;
//
//            for (AbstractField field : form.getFields()) {
//
//                if (field instanceof FieldSetTag) {
//                    FieldSetTag fieldsetTag = (FieldSetTag) field;
//
//                    if (fieldsetTag.isFieldSetTagStart()) {
//                        if(mustStart){
//                            mustStart = false;
//                        }else{
//                            result=false;
//                            break;
//                        }
//                    }else{
//                        if(mustStart){
//                            result=false;
//                            break;
//                        }else{
//                            if(elementBetweenFieldSetTag<=0){
//                                result=false;
//                                break;
//                            }
//                            mustStart=true;
//                        }
//                    }
//
//                }
//
//                if(!mustStart){
//                    elementBetweenFieldSetTag++;
//                }else{
//                    elementBetweenFieldSetTag = -1;
//                }
//
//            }
//
//            if(!mustStart){
//                result=false;
//            }
//
//            return result;
//        }
//    }
//}
