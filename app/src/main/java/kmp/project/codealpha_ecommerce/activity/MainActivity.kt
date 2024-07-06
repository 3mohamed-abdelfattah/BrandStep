package kmp.project.codealpha_ecommerce.activity

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import kmp.project.codealpha_ecommerce.Adapter.BrandAdapter
import kmp.project.codealpha_ecommerce.Adapter.PopularAdapter
import kmp.project.codealpha_ecommerce.Adapter.SliderAdapter
import kmp.project.codealpha_ecommerce.Model.SliderModel
import kmp.project.codealpha_ecommerce.ViewModel.MainViewModel
import kmp.project.codealpha_ecommerce.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private val viewModel = MainViewModel()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBanners()
        initBrand()
        initPopular()

    }

    // Banners

    private fun initBanners() {
        binding.progressBarBanner.visibility = View.VISIBLE
        viewModel.banner.observe(this, Observer { item ->
            Banners(item)
            binding.progressBarBanner.visibility = View.GONE
        })
        viewModel.loadBanner()
    }

    private fun Banners(images: List<SliderModel>) {
        binding.viewPageSlider.adapter = SliderAdapter(images, binding.viewPageSlider)
        binding.viewPageSlider.clipToPadding = false
        binding.viewPageSlider.clipChildren = false
        binding.viewPageSlider.offscreenPageLimit = 3
        binding.viewPageSlider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
        }
        binding.viewPageSlider.setPageTransformer(compositePageTransformer)
        if (images.size > 1) {
            binding.dotIndicator.visibility = View.VISIBLE
            binding.dotIndicator.attachTo(binding.viewPageSlider)
        }
    }


    //Brands

    private fun initBrand() {
        binding.progressBarBrand.visibility = View.VISIBLE
        viewModel.brands.observe(this, Observer {
            binding.viewBrand.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            binding.viewBrand.adapter = BrandAdapter(it)
            binding.progressBarBrand.visibility = View.GONE
        })
        viewModel.loadBrand()
    }


    //Popular

    private fun initPopular() {
        binding.progressBarRecommend.visibility = View.VISIBLE
        viewModel.popular.observe(this, Observer {
            binding.viewRecommend.layoutManager = GridLayoutManager(this@MainActivity, 2)
            binding.viewRecommend.adapter = PopularAdapter(it)
            binding.progressBarRecommend.visibility = View.GONE
        })
        viewModel.loadPopular()
    }

}
