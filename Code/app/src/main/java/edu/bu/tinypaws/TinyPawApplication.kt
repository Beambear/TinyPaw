package edu.bu.tinypaws

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import edu.bu.tinypaws.objects.Food
import edu.bu.tinypaws.objects.Pet
import edu.bu.tinypaws.repository.FoodRepository
import edu.bu.tinypaws.repository.MyPetRepository
import edu.bu.tinypaws.room.TinyPawDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

class TinyPawApplication:Application() {
    lateinit var tinyPawDatabase: TinyPawDatabase
    lateinit var myPetRepository: MyPetRepository
    lateinit var myFoodRepository: FoodRepository
    override fun onCreate() {
        super.onCreate()
        tinyPawDatabase = Room.databaseBuilder(
            applicationContext, TinyPawDatabase::class.java,
            "tinyPaw-db"
        ).addCallback(object: RoomDatabase.Callback(){  //a callback to modify onCreate() to set default
            override fun onCreate(db: SupportSQLiteDatabase){
                super.onCreate(db)
                defaultHome()
            }
        }
        ).build()

        myPetRepository = MyPetRepository(tinyPawDatabase.myPetDao())
        myFoodRepository = FoodRepository(tinyPawDatabase.myFoodDao())

    }

    fun defaultHome(){
        Executors.newSingleThreadScheduledExecutor().execute{
            GlobalScope.launch{
                tinyPawDatabase.myPetDao().addPet(
                    Pet(
                        1,
                        "momo",
                        "none",
                        "American short hair",
                        "male",
                        7.0,
                        "2011/04/05",
                        "",
                        "my first cat",
                    )
                )

                tinyPawDatabase.myPetDao().addPet(
                    Pet(
                        2,
                        "cookie",
                        "none",
                        "Rag Doll",
                        "male",
                        6.5,
                        "2014/10/05",
                        "",
                        "my second cat",
                    )
                )

                tinyPawDatabase.myFoodDao().addMyFood(
                    Food(
                        0,
                        "---Empty---",
                        "",
                        0
                    )
                )

                tinyPawDatabase.myFoodDao().addMyFood(
                    Food(
                        0,
                        "TP dry food",
                        "TinyPaw",
                        4000
                    )
                )

                tinyPawDatabase.myFoodDao().addMyFood(
                    Food(
                        0,
                        "OJ chicken",
                        "Orijen",
                        3390
                    )
                )
            }
        }
    }
}