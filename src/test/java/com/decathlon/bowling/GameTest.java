package com.decathlon.bowling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class GameTest {

  private Game game;

  @BeforeEach
  void init() {
    game = new Game();
  }

  // Step 1 - No Spare / No Strike
  @Test
  void step1_calculate_score_first_roll() {
    game.setRolls(Arrays.asList(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
    assertThat(game.getScore(), is(1));
  }

  @Test
  void step1_calculate_score_with_another_first_roll() {
    game.setRolls(Arrays.asList(2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
    assertThat(game.getScore(), is(2));
  }

  @Test
  void step1_calculate_score_for_two_rolls() {
    game.setRolls(Arrays.asList(3, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
    assertThat(game.getScore(), is(5));
  }

  @Test
  void step1_calculate_score_for_twenty_rolls() {
    game.setRolls(Arrays.asList(1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2));
    assertThat(game.getScore(), is(30));
  }

  @Test
  void step1_calculate_score_without_rolls() {
    assertThat(game.getScore(), is(0));
  }

  // Step 2 - With Spares
  @Test
  void step2_calculate_score_with_spare_without_additional_point() {
    game.setRolls(Arrays.asList(1, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
    assertThat(game.getScore(), is(10));
  }

  @Test
  void step2_calculate_score_with_spare() {
    game.setRolls(Arrays.asList(1, 9, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
    assertThat(game.getScore(), is(12));
  }

  @Test
  void step2_calculate_score_with_spares() {
    game.setRolls(Arrays.asList(1, 9, 1, 9, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
    assertThat(game.getScore(), is(23));
  }

  // Step 3 - With Strikes
  @Test
  void step3_calculate_score_with_strike_without_additional_point() {
    game.setRolls(Arrays.asList(10, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
    assertThat(game.getScore(), is(11));
  }

  void step3_calculate_score_with_strike() {
    game.setRolls(Arrays.asList(10, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
    assertThat(game.getScore(), is(13));
  }

  @Test
  void step3_calculate_score_with_strikes() {
    game.setRolls(Arrays.asList(10, 1, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
    assertThat(game.getScore(), is(20));
  }

  @Test
  void step3_calculate_score_two_strikes_in_a_row() {
    game.setRolls(Arrays.asList(10, 10, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
    assertThat(game.getScore(), is(42));
  }

  // Step 4 - With Bonus
  @Test
  void step4_calculate_score_with_spare_in_last_frame_without_bonus() {
    game.setRolls(Arrays.asList(10, 10, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0));
    assertThat(game.getScore(), is(52));
  }

  @Test
  void step4_calculate_score_with_spare_bonus() {
    game.setRolls(Arrays.asList(10, 10, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 5));
    assertThat(game.getScore(), is(57));
  }

  @Test
  void step4_calculate_score_with_strike_bonus() {
    game.setRolls(Arrays.asList(10, 10, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 7, 2));
    assertThat(game.getScore(), is(61));
  }

  @Test
  void step4_calculate_score_max() {
    game.setRolls(Arrays.asList(10, 10,10, 10,10, 10,10, 10,10, 10,10, 10));
    assertThat(game.getScore(), is(300));
  }
}
