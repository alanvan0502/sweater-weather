package com.alanvan.punters_weather.data.data_source

import android.arch.persistence.room.Room
import android.support.test.runner.AndroidJUnit4
import com.alanvan.punters_weather.data.dao.CountryDao
import com.alanvan.punters_weather.data.dao.SportDao
import com.alanvan.punters_weather.data.dao.WeatherDao
import com.alanvan.punters_weather.data.model.Country
import com.alanvan.punters_weather.data.model.Sport
import com.alanvan.punters_weather.data.model.VenueWeatherData
import com.alanvan.punters_weather.data.room.WeatherDataBase
import com.alanvan.punters_weather.injection.Injector
import com.nhaarman.mockito_kotlin.mock
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LocalDataSourceTest {

    private lateinit var weatherDao: WeatherDao
    private lateinit var countryDao: CountryDao
    private lateinit var sportDao: SportDao
    private lateinit var db: WeatherDataBase
    private lateinit var listVenueWeatherData: ArrayList<VenueWeatherData>

    private val localDataSource: LocalDataSource = mock()

    @Before
    fun setUp() {
        // prepare data
        listVenueWeatherData = ArrayList()
        for (i in 0 until 400) {
            listVenueWeatherData.add(buildVenueWeatherData(i))
        }

        for (i in 400 until 500) {
            listVenueWeatherData.add(buildVenueWeatherData(i, "country_499"))
        }

        // create db
        val context = Injector.getContextComponent().appContext()
        db = Room.inMemoryDatabaseBuilder(context, WeatherDataBase::class.java).build()
        weatherDao = db.weatherDao()
        countryDao = db.countryDao()
        sportDao = db.sportDao()

        localDataSource.apply {
            setSportDao(sportDao)
            setWeatherDao(weatherDao)
            setCountryDao(countryDao)
        }

    }

    private fun buildVenueWeatherData(i: Int, countryId: String = "country_$i"): VenueWeatherData {
        return VenueWeatherData().apply {
            setVenueID("id_$i")
            setName("name_$i")
            setCountry(Country().apply {
                this.setName("country")
                this.setCountryID(countryId)
            })
            setCountryID(countryId)
            setSport(Sport().apply {
                this.setSportID("sport_$i")
                this.setDescription("sport")
            })
            setSportID("sport_$i")
            setWeatherCondition("weather_condition_$i")
            setWeatherTemp(i)
            setWeatherLastUpdated(i)
        }
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun saveWeatherData() {
        localDataSource.saveWeatherData(listVenueWeatherData)
        assert(weatherDao.getWeatherData().size == listVenueWeatherData.size)
        assert(sportDao.getAllSports().size == listVenueWeatherData.size)
        assert(countryDao.getAllCountries().size == listVenueWeatherData.size)
    }

    @Test
    fun onGetWeatherDataSortedByAlphabet() {
        localDataSource.saveWeatherData(listVenueWeatherData)
        val result1 = localDataSource.onGetWeatherDataSortedByAlphabet(true, "0").blockingFirst()
        assert(result1.isEmpty())

        val result2 = localDataSource.onGetWeatherDataSortedByAlphabet(true, "country_50").blockingFirst()
        assert(result2.size == 1)
        assert(result2[0].getVenueID() == "id_50")

        val result3 = localDataSource.onGetWeatherDataSortedByAlphabet(true, "country_499").blockingFirst()
        assert(result3.size == 100)
        assert(result3[0].getVenueID() == "id_100")
        assert(result3[99].getVenueID() == "id_499")

        val result4 = localDataSource.onGetWeatherDataSortedByAlphabet(false, "country_499").blockingFirst()
        assert(result4.size == 100)
        assert(result4[0].getVenueID() == "id_499")
        assert(result4[99].getVenueID() == "id_100")
    }

    @Test
    fun onGetWeatherDataSortedByTemperature() {
        localDataSource.saveWeatherData(listVenueWeatherData)
        val result1 = localDataSource.onGetWeatherDataSortedByTemperature(true, "0").blockingFirst()
        assert(result1.isEmpty())

        val result2 = localDataSource.onGetWeatherDataSortedByTemperature(true, "country_50").blockingFirst()
        assert(result2.size == 1)
        assert(result2[0].getVenueID() == "id_50")

        val result3 = localDataSource.onGetWeatherDataSortedByTemperature(true, "country_499").blockingFirst()
        assert(result3.size == 100)
        assert(result3[0].getVenueID() == "id_100")
        assert(result3[99].getVenueID() == "id_499")

        val result4 = localDataSource.onGetWeatherDataSortedByTemperature(false, "country_499").blockingFirst()
        assert(result4.size == 100)
        assert(result4[0].getVenueID() == "id_499")
        assert(result4[99].getVenueID() == "id_100")
    }

    @Test
    fun onGetWeatherDataSortedByLastUpdated() {
        localDataSource.saveWeatherData(listVenueWeatherData)
        val result1 = localDataSource.onGetWeatherDataSortedByLastUpdated(true, "0").blockingFirst()
        assert(result1.isEmpty())

        val result2 = localDataSource.onGetWeatherDataSortedByLastUpdated(true, "country_50").blockingFirst()
        assert(result2.size == 1)
        assert(result2[0].getVenueID() == "id_50")

        val result3 = localDataSource.onGetWeatherDataSortedByLastUpdated(true, "country_499").blockingFirst()
        assert(result3.size == 100)
        assert(result3[0].getVenueID() == "id_100")
        assert(result3[99].getVenueID() == "id_499")

        val result4 = localDataSource.onGetWeatherDataSortedByLastUpdated(false, "country_499").blockingFirst()
        assert(result4.size == 100)
        assert(result4[0].getVenueID() == "id_499")
        assert(result4[99].getVenueID() == "id_100")
    }

    @Test
    fun onGetWeatherData() {
        localDataSource.saveWeatherData(listVenueWeatherData)
        val result1 = localDataSource.onGetWeatherData().blockingFirst()
        assert(result1.size == 500)
    }

    @Test
    fun onGetAllCountries() {
        localDataSource.saveWeatherData(listVenueWeatherData)
        val result1 = localDataSource.onGetAllCountries().blockingFirst()
        assert(result1.size == 500)
    }

    @Test
    fun onGetWeatherDataByCountryId() {
        localDataSource.saveWeatherData(listVenueWeatherData)
        val result1 = localDataSource.onGetWeatherDataByCountryId("0").blockingFirst()
        assert(result1.isEmpty())

        val result2 = localDataSource.onGetWeatherDataByCountryId("country_50").blockingFirst()
        assert(result2.size == 1)
        assert(result2[0].getVenueID() == "id_50")

        val result3 = localDataSource.onGetWeatherDataByCountryId("country_499").blockingFirst()
        assert(result3.size == 100)
        assert(result3[0].getVenueID() == "id_100")
        assert(result3[99].getVenueID() == "id_499")

        val result4 = localDataSource.onGetWeatherDataByCountryId("country_499").blockingFirst()
        assert(result4.size == 100)
        assert(result4[0].getVenueID() == "id_499")
        assert(result4[99].getVenueID() == "id_100")
    }

    @Test
    fun onGetWeatherDataByVenueId() {
        localDataSource.saveWeatherData(listVenueWeatherData)
        val result = localDataSource.onGetWeatherDataByVenueId("id_50").blockingFirst()
        assert(result.getWeatherTemp() == 50)
        assert(result.getName() == "name_50")
        assert(result.getCountryID() == "country_50")
        assert(result.getSportID() == "sport_50")
        assert(result.getWeatherLastUpdated() == 50)
        assert(result.getWeatherCondition() == "weather_condition_50")
    }
}