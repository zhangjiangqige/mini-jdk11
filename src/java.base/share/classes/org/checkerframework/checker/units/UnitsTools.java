package org.checkerframework.checker.units;

import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.checkerframework.checker.units.qual.K;
import org.checkerframework.checker.units.qual.cd;
import org.checkerframework.checker.units.qual.degrees;
import org.checkerframework.checker.units.qual.g;
import org.checkerframework.checker.units.qual.h;
import org.checkerframework.checker.units.qual.kg;
import org.checkerframework.checker.units.qual.km2;
import org.checkerframework.checker.units.qual.km;
import org.checkerframework.checker.units.qual.kmPERh;
import org.checkerframework.checker.units.qual.m2;
import org.checkerframework.checker.units.qual.m;
import org.checkerframework.checker.units.qual.mPERs2;
import org.checkerframework.checker.units.qual.mPERs;
import org.checkerframework.checker.units.qual.min;
import org.checkerframework.checker.units.qual.mm2;
import org.checkerframework.checker.units.qual.mm;
import org.checkerframework.checker.units.qual.mol;
import org.checkerframework.checker.units.qual.radians;
import org.checkerframework.checker.units.qual.s;

@SuppressWarnings({ "units", "checkstyle:constantname" })
public class UnitsTools {

    @mPERs2
    public static final int mPERs2;

    @radians
    public static final double rad;

    @degrees
    public static final double deg;

    @radians
    public static double toRadians(@degrees double angdeg);

    @degrees
    public static double toDegrees(@radians double angrad);

    @mm2
    public static final int mm2;

    @m2
    public static final int m2;

    @km2
    public static final int km2;

    @A
    public static final int A;

    @cd
    public static final int cd;

    @mm
    public static final int mm;

    @m
    public static final int m;

    @km
    public static final int km;

    @m
    public static int fromMilliMeterToMeter(@mm int mm);

    @mm
    public static int fromMeterToMilliMeter(@m int m);

    @km
    public static int fromMeterToKiloMeter(@m int m);

    @m
    public static int fromKiloMeterToMeter(@km int km);

    @g
    public static final int g;

    @kg
    public static final int kg;

    @kg
    public static int fromGramToKiloGram(@g int g);

    @g
    public static int fromKiloGramToGram(@kg int kg);

    @mPERs
    public static final int mPERs;

    @kmPERh
    public static final int kmPERh;

    @kmPERh
    public static double fromMeterPerSecondToKiloMeterPerHour(@mPERs double mps);

    @mPERs
    public static double fromKiloMeterPerHourToMeterPerSecond(@kmPERh double kmph);

    @mol
    public static final int mol;

    @K
    public static final int K;

    @C
    public static final int C;

    @C
    public static int fromKelvinToCelsius(@K int k);

    @K
    public static int fromCelsiusToKelvin(@C int c);

    @s
    public static final int s;

    @min
    public static final int min;

    @h
    public static final int h;

    @min
    public static int fromSecondToMinute(@s int s);

    @s
    public static int fromMinuteToSecond(@min int min);

    @h
    public static int fromMinuteToHour(@min int min);

    @min
    public static int fromHourToMinute(@h int h);
}
