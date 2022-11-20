package util;

import constant.NumberConstant;
import constant.StringConstant;
import java.util.List;

public class BridgeGameValidator {

    public static boolean isBridgeEqualsD(String bridgeIndex) {
        return bridgeIndex.equals(StringConstant.DOWN_SIDE.getConstant());
    }

    public static boolean isBridgeEqualsU(String bridgeIndex) {
        return bridgeIndex.equals(StringConstant.UP_SIDE.getConstant());
    }

    public static boolean isContainWrongPath(List<List<String>> bothSide) {
        return bothSide.get(NumberConstant.UP_SIDE.getConstant()).contains(StringConstant.WRONG_PATH.getConstant())
                || bothSide.get(NumberConstant.DOWN_SIDE.getConstant()).contains(StringConstant.WRONG_PATH.getConstant());
    }

    public static boolean isUserInputRetry(String retryOrQuit) {
        return retryOrQuit.equals(StringConstant.RETRY.getConstant());
    }
}
