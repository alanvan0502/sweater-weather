package com.alanvan.sweater_weather.data.room

import android.arch.persistence.room.Room
import android.support.test.runner.AndroidJUnit4
import com.alanvan.sweater_weather.data.dao.CountryDao
import com.alanvan.sweater_weather.data.dao.SportDao
import com.alanvan.sweater_weather.data.dao.WeatherDao
import com.alanvan.sweater_weather.data.model.Country
import com.alanvan.sweater_weather.data.model.Sport
import com.alanvan.sweater_weather.data.model.VenueWeatherData
import com.alanvan.sweater_weather.injection.Injector
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class WeatherDataBaseTest {

    private lateinit var weatherDao: WeatherDao
    private lateinit var countryDao: CountryDao
    private lateinit var sportDao: SportDao
    private lateinit var db: WeatherDataBase

    private lateinit var venueWeatherData1: VenueWeatherData
    private lateinit var venueWeatherData2: VenueWeatherData
    private lateinit var venueWeatherData3: VenueWeatherData
    private lateinit var listVenueWeatherData: ArrayList<VenueWeatherData>

    @Before
    fun createDb() {
        val context = Injector.getContextComponent().appContext()
        db = Room.inMemoryDatabaseBuilder(context, WeatherDataBase::class.java).build()
        weatherDao = db.weatherDao()
        countryDao = db.countryDao()
        sportDao = db.sportDao()

        listVenueWeatherData = ArrayList()
        for(i in 0 until 400) {
            val data = VenueWeatherData().apply {
                setVenueID("id_$i")
                setName("name_$i")
                setCountryID("country_$i")
                setSportID("sport_$i")
                setWeatherCondition("weather_condition_$i")
                setWeatherTemp(i)
                setWeatherLastUpdated(i)
            }
            listVenueWeatherData.add(data)
        }
        weatherDao.insert(listVenueWeatherData)

        venueWeatherData1 = VenueWeatherData().apply {
            setVenueID("id_401")
            setName("name_1")
            setCountryID("country_id_1")
            setSportID("sport_id_1")
            setWeatherTemp(1)
            setWeatherLastUpdated(1)
        }

        venueWeatherData2 = VenueWeatherData().apply {
            setVenueID("id_402")
            setName("name_2")
            setCountryID("country_id_2")
            setSportID("sport_id_2")
            setWeatherTemp(2)
            setWeatherLastUpdated(2)
        }

        venueWeatherData3 = VenueWeatherData().apply {
            setVenueID("id_403")
            setName("name_3")
            setCountryID("country_id_3")
            setSportID("sport_id_3")
            setWeatherCondition("test_weather_condition_3")
            setWeatherTemp(3)
            setWeatherLastUpdated(3)
        }
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    /**
     * Test WeatherDao functions
     */

    @Test
    @Throws(Exception::class)
    fun insertSingleDataToVenueWeatherDataTable() {
        weatherDao.insert(venueWeatherData1)
        assertTrue(weatherDao.getWeatherData().size == 401)
    }

    @Test
    @Throws(Exception::class)
    fun insertListDataToVenueWeatherDataTable() {
        weatherDao.insert(listOf(venueWeatherData1, venueWeatherData2, venueWeatherData3))
        assertTrue(weatherDao.getWeatherData().size == 403)
    }

    @Test
    @Throws(Exception::class)
    fun testGetWeatherDataSortedByAlphabet() {
        val listResultAscending = weatherDao.getWeatherDataSortedByTemperature(true)
        val listResultDescending = weatherDao.getWeatherDataSortedByTemperature(false)
        assertTrue(listResultAscending[0].getVenueID() == "id_0")
        assertTrue(listResultAscending[399].getVenueID() == "id_399")
        assertTrue(listResultDescending[399].getVenueID() == "id_0")
        assertTrue(listResultDescending[0].getVenueID() == "id_399")
    }

    @Test
    @Throws(Exception::class)
    fun testGetWeatherDataSortedByTemperature() {
        val listResultAscending = weatherDao.getWeatherDataSortedByTemperature(true)
        val listResultDescending = weatherDao.getWeatherDataSortedByTemperature(false)
        assertTrue(listResultAscending[0].getVenueID() == "id_0")
        assertTrue(listResultAscending[399].getVenueID() == "id_399")
        assertTrue(listResultDescending[399].getVenueID() == "id_0")
        assertTrue(listResultDescending[0].getVenueID() == "id_399")
    }

    @Test
    @Throws(Exception::class)
    fun getWeatherDataSortedByByLastUpdated() {
        val listResultAscending = weatherDao.getWeatherDataSortedByByLastUpdated(true)
        val listResultDescending = weatherDao.getWeatherDataSortedByByLastUpdated(false)
        assertTrue(listResultAscending[0].getVenueID() == "id_0")
        assertTrue(listResultAscending[399].getVenueID() == "id_399")
        assertTrue(listResultDescending[399].getVenueID() == "id_0")
        assertTrue(listResultDescending[0].getVenueID() == "id_399")
    }

    @Test
    @Throws(Exception::class)
    fun getWeatherDataSortedByAlphabetFiltered() {
        weatherDao.insert(VenueWeatherData().apply {
            setVenueID("id_401")
            setName("name_401")
            setCountryID("country_1")
            setSportID("sport_401")
            setWeatherCondition("weather_condition_401")
            setWeatherTemp(401)
            setWeatherLastUpdated(401)
        })

        val listResultAscending = weatherDao.getWeatherDataSortedByAlphabetFiltered(true, "country_1")
        val listResultDescending = weatherDao.getWeatherDataSortedByAlphabetFiltered(false, "country_1")
        assertTrue(listResultAscending.size == listResultDescending.size)
        assertTrue(listResultAscending[0].getVenueID() == "id_1")
        assertTrue(listResultAscending[1].getVenueID() == "id_401")
        assertTrue(listResultDescending[1].getVenueID() == "id_1")
        assertTrue(listResultDescending[0].getVenueID() == "id_401")
    }

    @Test
    @Throws(Exception::class)
    fun getWeatherDataSortedByTemperatureFiltered() {
        weatherDao.insert(VenueWeatherData().apply {
            setVenueID("id_401")
            setName("name_401")
            setCountryID("country_1")
            setSportID("sport_401")
            setWeatherCondition("weather_condition_401")
            setWeatherTemp(401)
            setWeatherLastUpdated(401)
        })

        val listResultAscending = weatherDao.getWeatherDataSortedByTemperatureFiltered(true, "country_1")
        val listResultDescending = weatherDao.getWeatherDataSortedByTemperatureFiltered(false, "country_1")
        assertTrue(listResultAscending.size == listResultDescending.size)
        assertTrue(listResultAscending[0].getVenueID() == "id_1")
        assertTrue(listResultAscending[1].getVenueID() == "id_401")
        assertTrue(listResultDescending[1].getVenueID() == "id_1")
        assertTrue(listResultDescending[0].getVenueID() == "id_401")
    }

    @Test
    @Throws(Exception::class)
    fun getWeatherDataSortedByByLastUpdatedFiltered() {
        weatherDao.insert(VenueWeatherData().apply {
            setVenueID("id_401")
            setName("name_401")
            setCountryID("country_1")
            setSportID("sport_401")
            setWeatherCondition("weather_condition_401")
            setWeatherTemp(401)
            setWeatherLastUpdated(401)
        })

        val listResultAscending = weatherDao.getWeatherDataSortedByByLastUpdatedFiltered(true, "country_1")
        val listResultDescending = weatherDao.getWeatherDataSortedByByLastUpdatedFiltered(false, "country_1")
        assertTrue(listResultAscending.size == listResultDescending.size)
        assertTrue(listResultAscending[0].getVenueID() == "id_1")
        assertTrue(listResultAscending[1].getVenueID() == "id_401")
        assertTrue(listResultDescending[1].getVenueID() == "id_1")
        assertTrue(listResultDescending[0].getVenueID() == "id_401")
    }

    @Test
    @Throws(Exception::class)
    fun getWeatherDataByCountryId() {
        val listResult = weatherDao.getWeatherDataByCountryId("country_150")
        assertTrue(listResult.size == 1)
        assertTrue(listResult[0].getVenueID() == "id_150")
    }

    @Test
    @Throws(Exception::class)
    fun getWeatherData() {
        assertTrue(weatherDao.getWeatherData().size == 400)
    }

    @Test
    @Throws(Exception::class)
    fun getWeatherDataByVenueId() {
        val result = weatherDao.getWeatherDataByVenueId("id_200")
        assertTrue(result.getVenueID() == "id_200")
    }

    /**
     * Test insert to SportDao
     */
    @Test
    @Throws(Exception::class)
    fun testInsertToSportTable() {
        val listData = ArrayList<Sport>()
        for(i in 0 until 400) {
            val data = Sport().apply {
                setSportID("sport_$i")
                setDescription("description_$i")
            }
            listData.add(data)
        }
        sportDao.insert(listData)
        assertTrue(sportDao.getAllSports().size == 400)
    }

    /**
     * Test insert to CountryDao
     */
    @Test
    @Throws(Exception::class)
    fun testInsertToCountryTableAndGetCountries() {
        val listData = ArrayList<Country>()
        for(i in 0 until 400) {
            val data = Country().apply {
                setCountryID("country_$i")
                setName("countryName_$i")
            }
            listData.add(data)
        }
        countryDao.insert(listData)
        assertTrue(countryDao.getAllCountries().size == 400)
    }
}