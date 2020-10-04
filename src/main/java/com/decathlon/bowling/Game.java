package com.decathlon.bowling;

import java.util.List;

public class Game {
  private static final int NUMBERS_OF_FRAMES_IN_BOWLING_GAME = 10;
  private static final int STRIKE_OR_SPARE_SCORE = 10;

  private static final int NEXT_CURRENT_INDEX = 2;
  private static final int NEXT_CURRENT_INDEX_AFTER_STRIKE = 1;

  private List<Integer> rolls;


  public void setRolls(List<Integer> rolls) {
    this.rolls = rolls;
  }

  public int getScore() {
    if (rolls == null) {
      return 0;
    }

    int score = 0;
    int currentRollIndex = 0;
    for (int i = 0; i < NUMBERS_OF_FRAMES_IN_BOWLING_GAME; i++) {
      if (isStrike(currentRollIndex)) {
        score += STRIKE_OR_SPARE_SCORE + calculateStrikeBonus(currentRollIndex);
        currentRollIndex += NEXT_CURRENT_INDEX_AFTER_STRIKE;
      } else if (isSpare(currentRollIndex)) {
        score += STRIKE_OR_SPARE_SCORE + calculateSpareBonus(currentRollIndex);
        currentRollIndex += NEXT_CURRENT_INDEX;
      } else {
        score += rolls.get(currentRollIndex) + rolls.get(currentRollIndex + 1);
        currentRollIndex += NEXT_CURRENT_INDEX;
      }
    }

    return score;
  }

  private Integer calculateSpareBonus(int currentRollIndex) {
    return rolls.get(currentRollIndex + 2);
  }

  private int calculateStrikeBonus(int currentRollIndex) {
    return rolls.get(currentRollIndex + 1) + rolls.get(currentRollIndex + 2);
  }

  private boolean isStrike(int currentRollIndex) {
    return rolls.get(currentRollIndex) == STRIKE_OR_SPARE_SCORE;
  }

  private boolean isSpare(int currentRollIndex) {
    return rolls.get(currentRollIndex) + rolls.get(currentRollIndex + 1) == STRIKE_OR_SPARE_SCORE;
  }
}
