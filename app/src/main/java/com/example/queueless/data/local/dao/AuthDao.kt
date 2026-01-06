package com.example.queueless.data.local.dao

import androidx.room.*
import com.example.queueless.data.local.entity.AuthEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AuthDao {

    @Query("SELECT * FROM auth LIMIT 1")
    fun getAuth(): Flow<AuthEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAuth(auth: AuthEntity)

    @Query("DELETE FROM auth")
    suspend fun clearAuth()
}
