package com.maksimzotov.weatherhelper

import com.google.gson.JsonElement
import com.maksimzotov.weatherhelper.data.logic.loadcity.CityDeserializer
import com.maksimzotov.weatherhelper.domain.entities.indicators.Temperature
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import java.lang.IllegalArgumentException

class CityDeserializerTest {
    val cityDeserializer = CityDeserializer()
    val correctJsonString = "{\"cod\":\"200\",\"message\":0,\"cnt\":40,\"list\":[{\"dt\":1628251200,\"main\":{\"temp\":292.36,\"feels_like\":292.22,\"temp_min\":292.36,\"temp_max\":292.69,\"pressure\":999,\"sea_level\":999,\"grnd_level\":996,\"humidity\":72,\"temp_kf\":-0.33},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":79},\"wind\":{\"speed\":6.51,\"deg\":245,\"gust\":11.2},\"visibility\":10000,\"pop\":1,\"rain\":{\"3h\":0.9},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-08-06 12:00:00\"},{\"dt\":1628262000,\"main\":{\"temp\":292.61,\"feels_like\":292.36,\"temp_min\":292.61,\"temp_max\":292.81,\"pressure\":1000,\"sea_level\":1000,\"grnd_level\":997,\"humidity\":67,\"temp_kf\":-0.2},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":74},\"wind\":{\"speed\":6.53,\"deg\":233,\"gust\":10.47},\"visibility\":10000,\"pop\":0.58,\"rain\":{\"3h\":0.46},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-08-06 15:00:00\"},{\"dt\":1628272800,\"main\":{\"temp\":290.51,\"feels_like\":290.29,\"temp_min\":290.51,\"temp_max\":290.51,\"pressure\":1000,\"sea_level\":1000,\"grnd_level\":997,\"humidity\":76,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":46},\"wind\":{\"speed\":5.91,\"deg\":229,\"gust\":10.48},\"visibility\":10000,\"pop\":0.71,\"rain\":{\"3h\":1.27},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-08-06 18:00:00\"},{\"dt\":1628283600,\"main\":{\"temp\":288.14,\"feels_like\":287.81,\"temp_min\":288.14,\"temp_max\":288.14,\"pressure\":1001,\"sea_level\":1001,\"grnd_level\":998,\"humidity\":81,\"temp_kf\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01n\"}],\"clouds\":{\"all\":3},\"wind\":{\"speed\":4.94,\"deg\":226,\"gust\":10.74},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2021-08-06 21:00:00\"},{\"dt\":1628294400,\"main\":{\"temp\":287.22,\"feels_like\":286.88,\"temp_min\":287.22,\"temp_max\":287.22,\"pressure\":1001,\"sea_level\":1001,\"grnd_level\":998,\"humidity\":84,\"temp_kf\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01n\"}],\"clouds\":{\"all\":5},\"wind\":{\"speed\":4.21,\"deg\":218,\"gust\":10.7},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2021-08-07 00:00:00\"},{\"dt\":1628305200,\"main\":{\"temp\":286.88,\"feels_like\":286.58,\"temp_min\":286.88,\"temp_max\":286.88,\"pressure\":1000,\"sea_level\":1000,\"grnd_level\":997,\"humidity\":87,\"temp_kf\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01n\"}],\"clouds\":{\"all\":6},\"wind\":{\"speed\":3.83,\"deg\":203,\"gust\":10.81},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2021-08-07 03:00:00\"},{\"dt\":1628316000,\"main\":{\"temp\":287.69,\"feels_like\":287.42,\"temp_min\":287.69,\"temp_max\":287.69,\"pressure\":999,\"sea_level\":999,\"grnd_level\":996,\"humidity\":85,\"temp_kf\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01d\"}],\"clouds\":{\"all\":6},\"wind\":{\"speed\":3.91,\"deg\":203,\"gust\":10.36},\"visibility\":10000,\"pop\":0.04,\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-08-07 06:00:00\"},{\"dt\":1628326800,\"main\":{\"temp\":290.98,\"feels_like\":290.65,\"temp_min\":290.98,\"temp_max\":290.98,\"pressure\":999,\"sea_level\":999,\"grnd_level\":996,\"humidity\":70,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":42},\"wind\":{\"speed\":6.02,\"deg\":202,\"gust\":9.35},\"visibility\":10000,\"pop\":0.57,\"rain\":{\"3h\":0.48},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-08-07 09:00:00\"},{\"dt\":1628337600,\"main\":{\"temp\":292,\"feels_like\":291.64,\"temp_min\":292,\"temp_max\":292,\"pressure\":998,\"sea_level\":998,\"grnd_level\":995,\"humidity\":65,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":60},\"wind\":{\"speed\":6.61,\"deg\":214,\"gust\":10.17},\"visibility\":10000,\"pop\":0.98,\"rain\":{\"3h\":1.02},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-08-07 12:00:00\"},{\"dt\":1628348400,\"main\":{\"temp\":292.26,\"feels_like\":291.87,\"temp_min\":292.26,\"temp_max\":292.26,\"pressure\":997,\"sea_level\":997,\"grnd_level\":994,\"humidity\":63,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":86},\"wind\":{\"speed\":5.63,\"deg\":215,\"gust\":9.34},\"visibility\":10000,\"pop\":0.88,\"rain\":{\"3h\":0.97},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-08-07 15:00:00\"},{\"dt\":1628359200,\"main\":{\"temp\":289.72,\"feels_like\":289.57,\"temp_min\":289.72,\"temp_max\":289.72,\"pressure\":998,\"sea_level\":998,\"grnd_level\":995,\"humidity\":82,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":93},\"wind\":{\"speed\":5.29,\"deg\":226,\"gust\":9.74},\"visibility\":6612,\"pop\":0.75,\"rain\":{\"3h\":0.93},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-08-07 18:00:00\"},{\"dt\":1628370000,\"main\":{\"temp\":288.3,\"feels_like\":288.25,\"temp_min\":288.3,\"temp_max\":288.3,\"pressure\":1000,\"sea_level\":1000,\"grnd_level\":996,\"humidity\":91,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":59},\"wind\":{\"speed\":5.19,\"deg\":240,\"gust\":11.73},\"visibility\":10000,\"pop\":0.94,\"rain\":{\"3h\":0.99},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2021-08-07 21:00:00\"},{\"dt\":1628380800,\"main\":{\"temp\":287.26,\"feels_like\":287.1,\"temp_min\":287.26,\"temp_max\":287.26,\"pressure\":1001,\"sea_level\":1001,\"grnd_level\":998,\"humidity\":91,\"temp_kf\":0},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04n\"}],\"clouds\":{\"all\":78},\"wind\":{\"speed\":5.44,\"deg\":244,\"gust\":12.05},\"visibility\":10000,\"pop\":0.78,\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2021-08-08 00:00:00\"},{\"dt\":1628391600,\"main\":{\"temp\":287.3,\"feels_like\":287.02,\"temp_min\":287.3,\"temp_max\":287.3,\"pressure\":1002,\"sea_level\":1002,\"grnd_level\":999,\"humidity\":86,\"temp_kf\":0},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"overcast clouds\",\"icon\":\"04n\"}],\"clouds\":{\"all\":98},\"wind\":{\"speed\":5.42,\"deg\":236,\"gust\":11.13},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2021-08-08 03:00:00\"},{\"dt\":1628402400,\"main\":{\"temp\":287.04,\"feels_like\":286.7,\"temp_min\":287.04,\"temp_max\":287.04,\"pressure\":1004,\"sea_level\":1004,\"grnd_level\":1001,\"humidity\":85,\"temp_kf\":0},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"overcast clouds\",\"icon\":\"04d\"}],\"clouds\":{\"all\":95},\"wind\":{\"speed\":5.16,\"deg\":236,\"gust\":12.22},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-08-08 06:00:00\"},{\"dt\":1628413200,\"main\":{\"temp\":289.06,\"feels_like\":288.51,\"temp_min\":289.06,\"temp_max\":289.06,\"pressure\":1006,\"sea_level\":1006,\"grnd_level\":1003,\"humidity\":69,\"temp_kf\":0},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"overcast clouds\",\"icon\":\"04d\"}],\"clouds\":{\"all\":95},\"wind\":{\"speed\":5.51,\"deg\":240,\"gust\":10.4},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-08-08 09:00:00\"},{\"dt\":1628424000,\"main\":{\"temp\":293.02,\"feels_like\":292.37,\"temp_min\":293.02,\"temp_max\":293.02,\"pressure\":1007,\"sea_level\":1007,\"grnd_level\":1004,\"humidity\":50,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":89},\"wind\":{\"speed\":6.63,\"deg\":244,\"gust\":9.45},\"visibility\":10000,\"pop\":0.2,\"rain\":{\"3h\":0.13},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-08-08 12:00:00\"},{\"dt\":1628434800,\"main\":{\"temp\":292.07,\"feels_like\":291.64,\"temp_min\":292.07,\"temp_max\":292.07,\"pressure\":1008,\"sea_level\":1008,\"grnd_level\":1005,\"humidity\":62,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":94},\"wind\":{\"speed\":6.01,\"deg\":232,\"gust\":9.36},\"visibility\":10000,\"pop\":0.74,\"rain\":{\"3h\":1.02},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-08-08 15:00:00\"},{\"dt\":1628445600,\"main\":{\"temp\":290.6,\"feels_like\":290.36,\"temp_min\":290.6,\"temp_max\":290.6,\"pressure\":1009,\"sea_level\":1009,\"grnd_level\":1006,\"humidity\":75,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":64},\"wind\":{\"speed\":5.31,\"deg\":226,\"gust\":9.57},\"visibility\":10000,\"pop\":0.68,\"rain\":{\"3h\":1.27},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-08-08 18:00:00\"},{\"dt\":1628456400,\"main\":{\"temp\":288.8,\"feels_like\":288.67,\"temp_min\":288.8,\"temp_max\":288.8,\"pressure\":1010,\"sea_level\":1010,\"grnd_level\":1007,\"humidity\":86,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":73},\"wind\":{\"speed\":4.92,\"deg\":212,\"gust\":10.66},\"visibility\":10000,\"pop\":0.52,\"rain\":{\"3h\":0.54},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2021-08-08 21:00:00\"},{\"dt\":1628467200,\"main\":{\"temp\":288.02,\"feels_like\":287.81,\"temp_min\":288.02,\"temp_max\":288.02,\"pressure\":1011,\"sea_level\":1011,\"grnd_level\":1007,\"humidity\":86,\"temp_kf\":0},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"overcast clouds\",\"icon\":\"04n\"}],\"clouds\":{\"all\":86},\"wind\":{\"speed\":4.25,\"deg\":229,\"gust\":10.99},\"visibility\":10000,\"pop\":0.72,\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2021-08-09 00:00:00\"},{\"dt\":1628478000,\"main\":{\"temp\":287.71,\"feels_like\":287.55,\"temp_min\":287.71,\"temp_max\":287.71,\"pressure\":1010,\"sea_level\":1010,\"grnd_level\":1007,\"humidity\":89,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10n\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":3.94,\"deg\":225,\"gust\":10.33},\"visibility\":10000,\"pop\":0.39,\"rain\":{\"3h\":0.24},\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2021-08-09 03:00:00\"},{\"dt\":1628488800,\"main\":{\"temp\":287.68,\"feels_like\":287.51,\"temp_min\":287.68,\"temp_max\":287.68,\"pressure\":1011,\"sea_level\":1011,\"grnd_level\":1008,\"humidity\":89,\"temp_kf\":0},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"overcast clouds\",\"icon\":\"04d\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":4.18,\"deg\":226,\"gust\":9.95},\"visibility\":10000,\"pop\":0.39,\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-08-09 06:00:00\"},{\"dt\":1628499600,\"main\":{\"temp\":291.12,\"feels_like\":290.8,\"temp_min\":291.12,\"temp_max\":291.12,\"pressure\":1012,\"sea_level\":1012,\"grnd_level\":1008,\"humidity\":70,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":49},\"wind\":{\"speed\":5.33,\"deg\":232,\"gust\":8.33},\"visibility\":10000,\"pop\":0.39,\"rain\":{\"3h\":0.15},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-08-09 09:00:00\"},{\"dt\":1628510400,\"main\":{\"temp\":290.77,\"feels_like\":290.62,\"temp_min\":290.77,\"temp_max\":290.77,\"pressure\":1012,\"sea_level\":1012,\"grnd_level\":1009,\"humidity\":78,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":68},\"wind\":{\"speed\":5.12,\"deg\":233,\"gust\":8.92},\"visibility\":10000,\"pop\":0.63,\"rain\":{\"3h\":1.06},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-08-09 12:00:00\"},{\"dt\":1628521200,\"main\":{\"temp\":292.44,\"feels_like\":292.23,\"temp_min\":292.44,\"temp_max\":292.44,\"pressure\":1012,\"sea_level\":1012,\"grnd_level\":1009,\"humidity\":69,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":4.67,\"deg\":248,\"gust\":8.36},\"visibility\":10000,\"pop\":0.74,\"rain\":{\"3h\":1.4},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-08-09 15:00:00\"},{\"dt\":1628532000,\"main\":{\"temp\":291.99,\"feels_like\":291.68,\"temp_min\":291.99,\"temp_max\":291.99,\"pressure\":1013,\"sea_level\":1013,\"grnd_level\":1010,\"humidity\":67,\"temp_kf\":0},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"clouds\":{\"all\":85},\"wind\":{\"speed\":4.34,\"deg\":245,\"gust\":8.09},\"visibility\":10000,\"pop\":0.8,\"rain\":{\"3h\":0.82},\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-08-09 18:00:00\"},{\"dt\":1628542800,\"main\":{\"temp\":289.3,\"feels_like\":288.85,\"temp_min\":289.3,\"temp_max\":289.3,\"pressure\":1015,\"sea_level\":1015,\"grnd_level\":1011,\"humidity\":72,\"temp_kf\":0},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04n\"}],\"clouds\":{\"all\":66},\"wind\":{\"speed\":3.88,\"deg\":247,\"gust\":10.34},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2021-08-09 21:00:00\"},{\"dt\":1628553600,\"main\":{\"temp\":288.12,\"feels_like\":287.71,\"temp_min\":288.12,\"temp_max\":288.12,\"pressure\":1015,\"sea_level\":1015,\"grnd_level\":1012,\"humidity\":78,\"temp_kf\":0},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04n\"}],\"clouds\":{\"all\":83},\"wind\":{\"speed\":3.71,\"deg\":238,\"gust\":9.75},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2021-08-10 00:00:00\"},{\"dt\":1628564400,\"main\":{\"temp\":287.46,\"feels_like\":287.01,\"temp_min\":287.46,\"temp_max\":287.46,\"pressure\":1015,\"sea_level\":1015,\"grnd_level\":1012,\"humidity\":79,\"temp_kf\":0},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"overcast clouds\",\"icon\":\"04n\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":3.59,\"deg\":234,\"gust\":9.63},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2021-08-10 03:00:00\"},{\"dt\":1628575200,\"main\":{\"temp\":287.52,\"feels_like\":287.05,\"temp_min\":287.52,\"temp_max\":287.52,\"pressure\":1015,\"sea_level\":1015,\"grnd_level\":1012,\"humidity\":78,\"temp_kf\":0},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"overcast clouds\",\"icon\":\"04d\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":3.16,\"deg\":232,\"gust\":8.49},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-08-10 06:00:00\"},{\"dt\":1628586000,\"main\":{\"temp\":290.39,\"feels_like\":289.84,\"temp_min\":290.39,\"temp_max\":290.39,\"pressure\":1016,\"sea_level\":1016,\"grnd_level\":1013,\"humidity\":64,\"temp_kf\":0},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"overcast clouds\",\"icon\":\"04d\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":3.88,\"deg\":237,\"gust\":6.16},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-08-10 09:00:00\"},{\"dt\":1628596800,\"main\":{\"temp\":293.67,\"feels_like\":292.98,\"temp_min\":293.67,\"temp_max\":293.67,\"pressure\":1015,\"sea_level\":1015,\"grnd_level\":1012,\"humidity\":46,\"temp_kf\":0},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"overcast clouds\",\"icon\":\"04d\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":4.05,\"deg\":238,\"gust\":5.08},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-08-10 12:00:00\"},{\"dt\":1628607600,\"main\":{\"temp\":294.66,\"feels_like\":293.99,\"temp_min\":294.66,\"temp_max\":294.66,\"pressure\":1015,\"sea_level\":1015,\"grnd_level\":1012,\"humidity\":43,\"temp_kf\":0},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"overcast clouds\",\"icon\":\"04d\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":4.24,\"deg\":231,\"gust\":4.96},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-08-10 15:00:00\"},{\"dt\":1628618400,\"main\":{\"temp\":293.6,\"feels_like\":292.98,\"temp_min\":293.6,\"temp_max\":293.6,\"pressure\":1015,\"sea_level\":1015,\"grnd_level\":1011,\"humidity\":49,\"temp_kf\":0},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04d\"}],\"clouds\":{\"all\":84},\"wind\":{\"speed\":3.19,\"deg\":223,\"gust\":4.1},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-08-10 18:00:00\"},{\"dt\":1628629200,\"main\":{\"temp\":289.83,\"feels_like\":289.33,\"temp_min\":289.83,\"temp_max\":289.83,\"pressure\":1016,\"sea_level\":1016,\"grnd_level\":1013,\"humidity\":68,\"temp_kf\":0},\"weather\":[{\"id\":801,\"main\":\"Clouds\",\"description\":\"few clouds\",\"icon\":\"02n\"}],\"clouds\":{\"all\":13},\"wind\":{\"speed\":2.58,\"deg\":214,\"gust\":6.71},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"n\"},\"dt_txt\":\"2021-08-10 21:00:00\"},{\"dt\":1628640000,\"main\":{\"temp\":288.17,\"feels_like\":287.71,\"temp_min\":288.17,\"temp_max\":288.17,\"pressure\":1016,\"sea_level\":1016,\"grnd_level\":1013,\"humidity\":76,\"temp_kf\":0},\"weather\":[{\"id\":801,\"main\":\"Clouds\",\"description\":\"few clouds\",\"icon\":\"02d\"}],\"clouds\":{\"all\":12},\"wind\":{\"speed\":2.1,\"deg\":221,\"gust\":5.28},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-08-11 00:00:00\"},{\"dt\":1628650800,\"main\":{\"temp\":287.28,\"feels_like\":286.81,\"temp_min\":287.28,\"temp_max\":287.28,\"pressure\":1016,\"sea_level\":1016,\"grnd_level\":1012,\"humidity\":79,\"temp_kf\":0},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"overcast clouds\",\"icon\":\"04d\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":1.7,\"deg\":211,\"gust\":4.76},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-08-11 03:00:00\"},{\"dt\":1628661600,\"main\":{\"temp\":287.66,\"feels_like\":287.23,\"temp_min\":287.66,\"temp_max\":287.66,\"pressure\":1015,\"sea_level\":1015,\"grnd_level\":1012,\"humidity\":79,\"temp_kf\":0},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"overcast clouds\",\"icon\":\"04d\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":1.84,\"deg\":193,\"gust\":4.59},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-08-11 06:00:00\"},{\"dt\":1628672400,\"main\":{\"temp\":292.77,\"feels_like\":292.43,\"temp_min\":292.77,\"temp_max\":292.77,\"pressure\":1015,\"sea_level\":1015,\"grnd_level\":1012,\"humidity\":63,\"temp_kf\":0},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"overcast clouds\",\"icon\":\"04d\"}],\"clouds\":{\"all\":100},\"wind\":{\"speed\":3.58,\"deg\":211,\"gust\":6.09},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2021-08-11 09:00:00\"}],\"city\":{\"id\":2643743,\"name\":\"London\",\"coord\":{\"lat\":51.5085,\"lon\":-0.1257},\"country\":\"GB\",\"population\":1000000,\"timezone\":3600,\"sunrise\":1628224321,\"sunset\":1628278845}}"
    val incorrectJsonElement: JsonElement? = null

    @Test
    fun testGetTemperaturesFromJson() {
        val expected = mapOf(
            "2021-08-07" to Temperature(14, 19),
            "2021-08-08" to Temperature(14, 19),
            "2021-08-09" to Temperature(14, 20),
            "2021-08-10" to Temperature(15, 19),
            "2021-08-11" to Temperature(14, 22)
        )
        val actual = cityDeserializer.getTemperaturesFromJson(correctJsonString)
        assertEquals(expected, actual)
    }

    @Test
    fun testDeserialize() {
        assertThrows(IllegalArgumentException::class.java) {
            cityDeserializer.deserialize(incorrectJsonElement, null, null)
        }
    }
}