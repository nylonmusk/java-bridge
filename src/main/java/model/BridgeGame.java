package model;

import constant.NumberConstant;
import controller.BridgeController;
import java.util.ArrayList;
import java.util.List;
import util.BridgeGameValidator;
import util.CleanerUtil;
import util.CountUtil;

public class BridgeGame {
    public static int tryNumber = NumberConstant.FIRST_TRY.getConstant();
    private static List<String> userBridge;

    public static void move(List<String> bridge, List<List<String>> bothSide) {
        int firstIndex = NumberConstant.FIRST_INDEX.getConstant();
        userBridge = new ArrayList<>();
        addMoveInUserBridge();
        for (int index = firstIndex; index < userBridge.size(); index++) {
            isUserPathEqualsPath(bothSide, bridge, index);
            if (BridgeGameValidator.isMoveDone(bridge, bothSide)) {
                break;
            }
            addMoveInUserBridge();
        }
    }

    private static void retry(List<String> bridge, List<List<String>> bothSide) {
        String retryOrQuit = BridgeController.getGameCommand();
        BridgeController.printRetryOrQuit(retryOrQuit);
        if (BridgeGameValidator.isUserInputRetry(retryOrQuit)) {
            CountUtil.countTryNumber();
            CleanerUtil.clearBothSide(bothSide);
            move(bridge, bothSide);
        }
    }

    private static void addMoveInUserBridge() {
        userBridge.add(BridgeController.getMoving());
    }

    private static void isUserPathEqualsPath(List<List<String>> bothSide, List<String> bridge, int index) {
        if (userBridge.get(index).equals(bridge.get(index))) {
            addCorrectPath(bothSide, bridge, index);
            return;
        }
        if (!userBridge.get(index).equals(bridge.get(index))) {
            addWrongPath(bothSide, bridge, index);
        }
    }

    private static void addCorrectPath(List<List<String>> bothSide, List<String> bridge, int index) {
        if (BridgeGameValidator.isBridgeEqualsU(bridge.get(index))) {
            BothSide.addCorrectPathUpSide(bothSide);
        }
        if (BridgeGameValidator.isBridgeEqualsD(bridge.get(index))) {
            BothSide.addCorrectPathDownSide(bothSide);
        }
        printMoving(bothSide, userBridge.get(index));
    }

    private static void addWrongPath(List<List<String>> bothSide, List<String> bridge, int index) {
        if (BridgeGameValidator.isBridgeEqualsU(bridge.get(index))) {
            BothSide.addWrongPathDownSide(bothSide);
        }
        if (BridgeGameValidator.isBridgeEqualsD(bridge.get(index))) {
            BothSide.addWrongPathUpSide(bothSide);
        }
        printMoving(bothSide, userBridge.get(index));
        checkRetry(bridge, bothSide);
    }

    private static void checkRetry(List<String> bridge, List<List<String>> bothSide) {
        if (BridgeGameValidator.isContainWrongPath(bothSide)) {
            retry(bridge, bothSide);
        }
    }

    private static void printMoving(List<List<String>> bothSide, String userPath) {
        BridgeController.printMoving(userPath);
        BridgeController.printMap(bothSide);
    }
}
