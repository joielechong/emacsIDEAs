package org.hunmr.copycutwithoutselection.selector;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.util.TextRange;
import org.hunmr.common.CommandContext;

public class ParagraphSelector extends Selector {
    public ParagraphSelector(Editor editor) {
        super(editor);
    }

    private int getParagraphStartOffset(int caretOffset) {
        int offset = caretOffset;
        while (offset > 0) {
            if (offsetIsAtEmptyLine(offset - 1)) {
                break;
            }

            offset--;
        }

        return offset;
    }

    private int getParagraphEndOffset(int caretOffset) {
        int offset = caretOffset;
        int length = _docText.length();

        while (offset < length - 1) {
            if (charAt(offset) == '\n' && offsetIsAtEmptyLine(offset + 1)) {
                break;
            }

            offset++;
        }

        return offset;
    }

    private boolean offsetIsAtEmptyLine(int offset) {
        int lineNumber = _document.getLineNumber(offset);
        int lineStartOffset = _document.getLineStartOffset(lineNumber);
        int lineEndOffset = _document.getLineEndOffset(lineNumber);

        String line = _document.getText(new TextRange(lineStartOffset, lineEndOffset));

        return line.trim().isEmpty();
    }

    public TextRange getRange(CommandContext cmdCtx) {
        final int caretOffset = getNearestStringEndOffset(_editor);

        int paraStart = getParagraphStartOffset(caretOffset);
        int paraEnd = getParagraphEndOffset(caretOffset);

        return paraEnd > paraStart ? new TextRange(paraStart, paraEnd) : null;
    }
}