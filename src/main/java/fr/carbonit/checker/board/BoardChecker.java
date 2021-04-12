package fr.carbonit.checker.board;

import fr.carbonit.checker.AbstractParameterChecker;
import fr.carbonit.checker.PositionChecker;
import fr.carbonit.checker.Violation;
import fr.carbonit.model.parameters.Board;
import lombok.NonNull;

import java.util.List;
import java.util.function.Function;

public class BoardChecker extends AbstractParameterChecker<Board> {
  private int boardCounter = 0;

  @Override
  public List<Function<Board, Violation>> getChecks() {
    return List.of(this::isBoardSizeLegal, this::isBoardUnique);
  }

  public Violation isBoardSizeLegal(@NonNull Board board) {
    if (!PositionChecker.isLegalDimension(board.getPosition()))
      return new BoardIllegalSizeViolation(board.getPosition());
    return null;
  }

  public Violation isBoardUnique(@NonNull Board board) {
    boardCounter++;
    return boardCounter == 1 ? null : new BoardNotUniqueViolation(boardCounter);
  }
}
