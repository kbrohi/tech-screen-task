package com.task.kaleem.uuid.service;

import java.util.Random;
import org.springframework.stereotype.Service;
import com.task.kaleem.uuid.domain.Response;
import com.task.kaleem.uuid.util.TaskUtil;


@Service
public class UUIDGeneratorService {

  int evenCount = 0;
  int oddCount = 0;

  public Response processUUIDGenerator(Integer x, Integer y, Integer z) {

    StringBuilder sequence = null;


    Response response = new Response();

    boolean isExecute = true;

    while (isExecute) {

      sequence = processSequence(x, y, z);

      if (evenCount == oddCount) {
        sequence = finalizeSequence(sequence);
        isExecute = false;
      } else {
        evenCount = 0;
        oddCount = 0;
        z++;
        sequence.setLength(0);
      }
    }

    response.setUuidSequence(sequence.toString());
    return response;

  }

  private StringBuilder finalizeSequence(StringBuilder sequence) {

    int len = sequence.length();

    if (len < 30) {
      for (int i = 0; i < 30 - len; i++)
        sequence.append("0");

    } else if (len > 40) {
      sequence.setLength(40);

    }

    return sequence;
  }

  private StringBuilder processSequence(int x, int y, int z) {

    StringBuilder sequence = new StringBuilder();
    int randomGeneratedValue = 0;


    while (z > 0) {
      randomGeneratedValue = getRandomNumber();

      if (isNumberEven(randomGeneratedValue)) {
        randomGeneratedValue = randomGeneratedValue / x;
        evenCount++;
      } else {
        randomGeneratedValue = randomGeneratedValue + y;
        oddCount++;
      }

      sequence.append(TaskUtil.longestPalSubseq(String.valueOf(randomGeneratedValue)));
      z--;

    }

    return sequence;

  }

  private int getRandomNumber() {

    Random random = new Random();
    int randomInteger = random.nextInt();

    return randomInteger;
  }


  private boolean isNumberEven(int number) {
    if (number % 2 == 0)
      return true;
    else
      return false;

  }
}
