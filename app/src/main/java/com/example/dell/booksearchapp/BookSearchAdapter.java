package com.example.dell.booksearchapp;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by dell on 1/26/2019.
 */

public class BookSearchAdapter extends RecyclerView.Adapter<BookSearchAdapter.myViewHolder> {

    ArrayList<Book> books = new ArrayList<>();
    Context context;
    int layout_id;

    public BookSearchAdapter()
    {

    }

    public BookSearchAdapter(Context context , ArrayList<Book> books , int layout_id)
    {
        this.books = books;
        this.context = context;
        this.layout_id = layout_id;

    }

    @NonNull
    @Override
    public BookSearchAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout_id , parent , false);
        myViewHolder holder = new myViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookSearchAdapter.myViewHolder holder, final int position) {
        holder.onBind(books.get(position));

        holder.selected_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , BookInformation.class);
                intent.putExtra("BookId" , books.get(position).getBookId());
                intent.putExtra("BookTitle" ,  books.get(position).getBookTitle());
                intent.putExtra("BookAuthor" ,  books.get(position).getBookAuthor());
                intent.putExtra("BookDescription" ,  books.get(position).getBookDescription());
                intent.putExtra("BookCover" ,  books.get(position).getBookCoverLink());
                intent.putExtra("Favorite" ,  books.get(position).isFavorite());
                intent.putExtra("AverageRating" ,  books.get(position).getAverageRating());
                intent.putExtra("Categories" ,  books.get(position).getBookCategories());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder
    {
        ImageView bookCover;
        TextView bookTitle;
        TextView bookAuthor;
        TextView bookRate;
        LinearLayout selected_book;

        public myViewHolder(View itemView) {
            super(itemView);

            bookCover = itemView.findViewById(R.id.bookCover);
            bookTitle = itemView.findViewById(R.id.bookTitle);
            bookAuthor = itemView.findViewById(R.id.bookAuthor);
            bookRate = itemView.findViewById(R.id.averageRate);
            selected_book = itemView.findViewById(R.id.item_selected);
        }

        public void onBind(Book book)
        {
            //load bookCover from internet and set it to imageView using Picasso library
            Picasso.get().load(book.getBookCoverLink()).into(bookCover);

            bookTitle.setText(book.getBookTitle());
            bookAuthor.setText(book.getBookAuthor());
            bookRate.setText(String.valueOf(book.getAverageRating()));
        }
    }
}