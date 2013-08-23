package org.hunmr.acejump;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import org.hunmr.acejump.command.CommandAroundJumpFactory;
import org.hunmr.util.EditorUtils;

public class AceJumpAndReplaceWordAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {

        EditorUtils.copyWord(e.getData(PlatformDataKeys.EDITOR));

        AceJumpAction.getInstance().addCommandsAroundJumpKey(CommandAroundJumpFactory.REPLACE_WORD_AFTER_JUMP);
        AceJumpAction.getInstance().performAction(e);
    }

}
