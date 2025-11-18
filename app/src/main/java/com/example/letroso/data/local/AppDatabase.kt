import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [WordEntity::class, UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun UserDao(): UserDao
}