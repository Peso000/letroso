import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.letroso.data.local.UserDao
import com.example.letroso.data.local.UserEntity
import com.example.letroso.data.local.WordDao
import com.example.letroso.data.local.WordEntity

@Database(entities = [WordEntity::class, UserEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun userDao(): UserDao
    abstract fun wordDao(): WordDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = try {
                    Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "letroso_db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }catch (e: Exception){
                    e.printStackTrace()
                    throw RuntimeException("Erro ao construir o room: ${e.message}", e)
                }

                INSTANCE = instance
                instance
            }
        }
    }
}