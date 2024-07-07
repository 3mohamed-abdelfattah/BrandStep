package kmp.project.codealpha_ecommerce.activity

import ManagmentCart
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kmp.project.codealpha_ecommerce.Adapter.ColorAdapter
import kmp.project.codealpha_ecommerce.Adapter.SizeAdapter
import kmp.project.codealpha_ecommerce.Adapter.SliderAdapter
import kmp.project.codealpha_ecommerce.Model.ItemsModel
import kmp.project.codealpha_ecommerce.Model.SliderModel
import kmp.project.codealpha_ecommerce.databinding.ActivityDetailBinding

class DetailActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var item: ItemsModel
    private var numberOrder = 1
    private lateinit var manageCart: ManagmentCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)



        manageCart = ManagmentCart(this)

        getBundle()
        banners()
        initList()

    }

    private fun initList() {
        val sizeList = ArrayList<String>()
        for (size in item.size) {
            sizeList.add(size.toString())
        }

        binding.sizeList.adapter = SizeAdapter(sizeList)
        binding.sizeList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val colorList = ArrayList<String>()
        for (imageUrl in item.picUrl) {
            colorList.add(imageUrl)
        }

        binding.colorList.adapter = ColorAdapter(colorList)
        binding.colorList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

    }

    private fun banners() {

        val sliderItems = ArrayList<SliderModel>()
        for (imageUrl in item.picUrl) {
            sliderItems.add(SliderModel(imageUrl))
        }
        binding.slider.adapter = SliderAdapter(sliderItems, binding.slider)
        binding.slider.clipToPadding = true
        binding.slider.clipChildren = true
        binding.slider.offscreenPageLimit = 1

        if (sliderItems.size > 1) {
            binding.dotIndicator.visibility = View.VISIBLE
            binding.dotIndicator.attachTo(binding.slider)

        }
    }

    private fun getBundle() {
        item = intent.getParcelableExtra("object")!!

        binding.titleText.text = item.title
        binding.discription.text = item.description
        binding.priceText.text = "$" + item.price
        binding.ratingText.text = "${item.rating} Rating"
        binding.addToCartBtn.setOnClickListener {
            item.numberInCart = numberOrder
            manageCart.insertFood(item)
        }
        binding.backBtn.setOnClickListener { finish() }
        binding.cartBtn.setOnClickListener {

        }
    }
}