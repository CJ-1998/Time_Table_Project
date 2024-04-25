package com.mysideproject.TimeTableProject.service;

public class DateTimeConstants {

    // 상수 클래스라 인스턴스 생성 방지
    private DateTimeConstants() {
    }

    // 상수 정의
    // 일주일의 일 수 나타내는 상수
    public static final int DAY_COUNT = 7;

    // 시작하는 시간을 나타내는 상수
    public static final int START_HOUR = 0;

    // 시작하는 분을 나타내는 상수
    public static final int START_MINUTE = 0;

    // 하루의 시간 개수를 나타내는 상수
    public static final int HOUR_COUNT = 24;

    // 한시간에 분의 개수를 나타내는 상수
    public static final int MINUTES_IN_HOUR = 60;

    // 설정한 계획표 시간 단위를 분으로 나타내는 상수
    public static final int INTERVAL_MINUTE = 30;

    // 한 시간에 시간 단위 개수 나타내는 상수
    public static final int INTERVAL_PER_HOUR = MINUTES_IN_HOUR / INTERVAL_MINUTE;
}
