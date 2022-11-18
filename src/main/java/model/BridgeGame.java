package model;

import constant.GameConstant;
import controller.BridgeController;
import constant.StringConstant;
import java.util.ArrayList;
import java.util.List;

public class BridgeGame {
    int tryNumber = 1;
    private int bridgeNumber = 0;

    public void run() {
        List<String> upSide = new ArrayList<>();
        List<String> downSide = new ArrayList<>();
        List<String> bridge = BridgeController.makeBridge();
        checkNextPath(bridge, upSide, downSide);
    }

    public List<String> move(List<String> bridge, String path) {
        BridgeController.printMoving(path);
        List<String> bridgeByUser = new ArrayList<>();

        if (isUserPathEqualsBridge(bridge, path)) {
            addCorrectPath(bridgeByUser, path);
        }
        if (!isUserPathEqualsBridge(bridge, path)) {
            addWrongPath(bridgeByUser, path);
        }
        countBridgeNumber();
        return bridgeByUser;
    }

    public void countBridgeNumber() {
        bridgeNumber++;
    }

    public void countTryNumber() {
        tryNumber++;
    }

    public void addCorrectPath(List<String> isCorrectBridge, String path) {
        isCorrectBridge.add(path);
        isCorrectBridge.add(StringConstant.CORRECT_PATH.getConstant());
    }

    public void addWrongPath(List<String> isCorrectBridge, String path) {
        isCorrectBridge.add(path);
        isCorrectBridge.add(StringConstant.WRONG_PATH.getConstant());
    }

    public boolean isUserPathEqualsBridge(List<String> bridge, String path) {
        return bridge.get(bridgeNumber).equals(path);
    }

    public void checkNextPath(List<String> bridge, List<String> upSide, List<String> downSide) {
        addCorrectResult(bridge, upSide, downSide);
        addWrongResult(bridge, upSide, downSide);
        if (UserInputDone(bridge, upSide, downSide)) {
            return;
        }
        checkNextPath(bridge, upSide, downSide);
    }

    private void addCorrectResult(List<String> bridge, List<String> upSide, List<String> downSide) {
        while (!isUserInputDone(bridge, upSide)) {
            addCorrectSymbolUpSideDownSide(bridge, upSide, downSide);
        }
    }

    private void addWrongResult(List<String> bridge, List<String> upSide, List<String> downSide) {
        while (!isUserInputDone(bridge, upSide)) {
            addWrongSymbolUpSideDownSide(bridge, upSide, downSide);
        }
    }

    private boolean UserInputDone(List<String> bridge, List<String> upSide, List<String> downSide) {
        if (isUserInputDone(bridge, upSide)) {
            BridgeController.printResult(upSide, downSide, tryNumber);
            return true;
        }
        return false;
    }

    public void addCorrectSymbolUpSideDownSide(List<String> bridge, List<String> upSide, List<String> downSide) {
        List<String> userMove = getUserMove(bridge);
        if (userMoveEqualsU(userMove) && userMoveCorrect(userMove)) {
            upSide.add(StringConstant.CORRECT_PATH.getConstant());
            downSide.add(StringConstant.BLANK.getConstant());
        }
        if (userMoveEqualsD(userMove) && userMoveCorrect(userMove)) {
            upSide.add(StringConstant.BLANK.getConstant());
            downSide.add(StringConstant.CORRECT_PATH.getConstant());
        }
        BridgeController.printMap(upSide, downSide);
    }

    public void addWrongSymbolUpSideDownSide(List<String> bridge, List<String> upSide, List<String> downSide) {
        List<String> userMove = getUserMove(bridge);
        if (userMoveEqualsU(userMove) && userMoveWrong(userMove)) {
            upSide.add(StringConstant.WRONG_PATH.getConstant());
            downSide.add(StringConstant.BLANK.getConstant());
        }
        if (userMoveEqualsD(userMove) && userMoveWrong(userMove)) {
            upSide.add(StringConstant.BLANK.getConstant());
            downSide.add(StringConstant.WRONG_PATH.getConstant());
        }
        BridgeController.printMap(upSide, downSide);
        retry(bridge, upSide, downSide);
    }

    public boolean isUserInputDone(List<String> bridge, List<String> upSide) {
        return bridge.size() == upSide.size();
    }

    public boolean userMoveEqualsU(List<String> userMove) {
        return userMove.get(GameConstant.FIRST_INDEX.getConstant()).equals(StringConstant.UP_SIDE.getConstant());
    }

    public boolean userMoveEqualsD(List<String> userMove) {
        return userMove.get(GameConstant.FIRST_INDEX.getConstant()).equals(StringConstant.DOWN_SIDE.getConstant());
    }

    public boolean userMoveCorrect(List<String> userMove) {
        return userMove.get(GameConstant.SECOND_INDEX.getConstant()).equals(StringConstant.CORRECT_PATH.getConstant());
    }

    public boolean userMoveWrong(List<String> userMove) {
        return userMove.get(GameConstant.SECOND_INDEX.getConstant()).equals(StringConstant.WRONG_PATH.getConstant());
    }

    public List<String> getUserMove(List<String> bridge) {
        return move(bridge, BridgeController.getMoving());
    }

    public void retry(List<String> bridge, List<String> upSide, List<String> downSide) {
        String retryOrQuit = BridgeController.getGameCommand();
        BridgeController.printRetryOrQuit(retryOrQuit);
        if (isUserInputRetry(retryOrQuit)) {
            countTryNumber();
            checkNextPath(bridge, upSide, downSide);
        }
        if (isUserInputQuit(retryOrQuit)) {
            BridgeController.printResult(upSide, downSide, tryNumber);
        }
    }

    public boolean isUserInputRetry(String retryOrQuit) {
        return retryOrQuit.equals(StringConstant.RETRY.getConstant());
    }

    public boolean isUserInputQuit(String retryOrQuit) {
        return retryOrQuit.equals(StringConstant.QUIT.getConstant());
    }
}
