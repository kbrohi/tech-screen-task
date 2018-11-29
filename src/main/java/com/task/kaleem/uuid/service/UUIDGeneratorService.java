package com.task.kaleem.uuid.service;

import java.util.Random;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.task.kaleem.uuid.domain.Response;
import com.task.kaleem.uuid.util.TaskUtil;

@Service
public class UUIDGeneratorService {

  int evenCount = 0;
  int oddCount = 0;

  public Response processUUIDGenerator(Integer x, Integer y, Integer z) {

    StringBuilder sequence = null;
    String uuidGeneratedSequence = null;

    Response response = new Response();

    boolean isExecute = true;
    if (z < 0)
      z = 1;
    while (isExecute) {

      sequence = processSequence(x, y, z);

      // When even and odd has equal representation so I can make final call and do the truncate and padding
      if (evenCount == oddCount) {
        uuidGeneratedSequence = finalizeSequence(sequence);
        isExecute = false;
      } else {
        evenCount = 0;
        oddCount = 0;
        z++;
        sequence.setLength(0);
      }

    }

    response.setUuidSequence(uuidGeneratedSequence);
    return response;

  }

  private String finalizeSequence(StringBuilder sequence) {

    String uuidSequence = sequence.toString().replace("\u0000", "");
    StringBuilder tmp = new StringBuilder();
    int len = uuidSequence.length();

    if (len < 30) {
      // Padding 0 to the current string so length can be 30,
      // here I can use different approaches, I can random function and padded with random number
      // also, I can get the plaindromic of remaining random number
      for (int i = 0; i < (30 - len); i++)
        tmp.append("0");
      uuidSequence += tmp.toString();
    } else if (len > 40) {
      // Here, truncating the values which are grater than 40
      uuidSequence = StringUtils.left(uuidSequence, 40);
    } else {
      // I have few cases where number could be above 30 and less than 40 so in this case I make 30 length,

      uuidSequence = StringUtils.left(uuidSequence, 30);
    }
    return uuidSequence;
  }

  private StringBuilder processSequence(int x, int y, int z) {

    StringBuilder sequence = new StringBuilder();
    int randomGeneratedValue = 0;

    while (z > 0) {
      // get the random generated number
      randomGeneratedValue = getRandomNumber();

      // to check the even or odd based on that if executes
      if (isNumberEven(randomGeneratedValue)) {
        randomGeneratedValue = randomGeneratedValue / x;
        // counting the even
        evenCount++;
      } else {
        randomGeneratedValue = randomGeneratedValue + y;
        // counting the odd
        oddCount++;
      }
      // Getting the longest Palindromic Sequence of randomly generated real number and appending in previous result
      sequence.append(TaskUtil.longestPalSubseq(String.valueOf(randomGeneratedValue)));

      // this condition I places to make sure than both even and odd has equal numbers, also I added and clause
      if (evenCount == oddCount && sequence.length() > 30) {
        // when this condition met I need to come out from loop so I set z=0 to make while loop false;
        z = 0;
        break;
      }
      z--;

    }

    return sequence;

  }

  /*
   * This Method will return the Randome generated number
   */
  private int getRandomNumber() {

    Random random = new Random();
    int randomInteger = random.nextInt();

    return randomInteger;
  }

  /*
   * This method responsible to check the number is even or odd
   */

  private boolean isNumberEven(int number) {
    if (number % 2 == 0)
      return true;
    else
      return false;

  }
}
