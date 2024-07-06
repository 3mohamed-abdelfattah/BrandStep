package kmp.project.codealpha_ecommerce.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kmp.project.codealpha_ecommerce.Model.BrandModel
import kmp.project.codealpha_ecommerce.Model.ItemModel
import kmp.project.codealpha_ecommerce.Model.SliderModel

class MainViewModel : ViewModel() {

    private val firebaseDatabase = FirebaseDatabase.getInstance()

    private val _banner = MutableLiveData<List<SliderModel>>()
    private val _brand = MutableLiveData<MutableList<BrandModel>>()
    private val _popular = MutableLiveData<MutableList<ItemModel>>()

    val popular: LiveData<MutableList<ItemModel>> = _popular
    val brands: LiveData<MutableList<BrandModel>> = _brand
    val banner: LiveData<List<SliderModel>> = _banner

    fun loadBanner() {
        val ref = firebaseDatabase.getReference("Banner")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<SliderModel>()
                for (childSnapShot in snapshot.children) {
                    val list = childSnapShot.getValue(SliderModel::class.java)
                    if (list != null) {
                        lists.add(list)
                    }
                }
                _banner.value = lists
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }


    fun loadBrand() {
        val ref = firebaseDatabase.getReference("Category")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<BrandModel>()
                for (childSnapShot in snapshot.children) {
                    val list = childSnapShot.getValue(BrandModel::class.java)
                    if (list != null) {
                        lists.add(list)
                    }
                }
                _brand.value = lists
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }



    fun loadPopular() {
        val ref = firebaseDatabase.getReference("Items")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<ItemModel>()
                for (childSnapShot in snapshot.children) {
                    val list = childSnapShot.getValue(ItemModel::class.java)
                    if (list != null) {
                        lists.add(list)
                    }
                }
                _popular.value = lists
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }
}