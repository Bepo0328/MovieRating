package kr.co.bepo.movierating.presentation.reviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.bepo.movierating.databinding.FragmentMovieReviewsBinding
import kr.co.bepo.movierating.domain.model.Movie
import kr.co.bepo.movierating.domain.model.Review
import kr.co.bepo.movierating.extension.toGone
import kr.co.bepo.movierating.extension.toVisible
import org.koin.android.scope.ScopeFragment
import org.koin.core.parameter.parametersOf

class MovieReviewsFragment : ScopeFragment(), MovieReviewsContract.View {


    override val presenter: MovieReviewsContract.Presenter by inject { parametersOf(arguments.movie) }

    private val arguments: MovieReviewsFragmentArgs by navArgs()
    private var binding: FragmentMovieReviewsBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentMovieReviewsBinding.inflate(inflater, container, false)
        .also { binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        presenter.onViewCreated()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun showLoadingIndicator() {
        binding?.progressBar?.toVisible()
    }

    override fun hideLoadingIndicator() {
        binding?.progressBar?.toGone()

    }

    override fun showErrorDescription(message: String) {
        binding?.recyclerView?.toGone()
        binding?.errorDescriptionTextView?.toVisible()
        binding?.errorDescriptionTextView?.text = message
    }

    override fun showMovieInformation(movie: Movie) {
        binding?.recyclerView?.adapter = MovieReviewsAdapter(movie)
    }

    override fun showReviews(reviews: List<Review>) {
        binding?.recyclerView?.toVisible()
        binding?.errorDescriptionTextView?.toGone()
        (binding?.recyclerView?.adapter as? MovieReviewsAdapter)?.apply {
            this.reviews = reviews
            notifyDataSetChanged()
        }
    }

    private fun initViews() {
        binding?.recyclerView?.apply {
            layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
        }
    }

}