package kmp.project.codealpha_ecommerce.activity

import android.os.Bundle
import kmp.project.codealpha_ecommerce.databinding.ActivityCartBinding

class CartActivity : BaseActivity() {
    private lateinit var binding: ActivityCartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCartBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}