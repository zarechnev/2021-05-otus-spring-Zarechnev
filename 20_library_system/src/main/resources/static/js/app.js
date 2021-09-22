$(document).ready(function() {
    reFillAllBooksArea();

    $("#btnForOpenAddingBookArea").click(function(){
        openingAddBookArea();
    });

    $("#saveBookBtn").click(function(){
        onSaveBookBtnClick();
    });

    $("#cancelBookBtn").click(function(){
        $('#bookAddForm').attr('hidden', true);
    });
});

function reFillAllBooksArea() {
    $.getJSON("/books", function(books) {
        let newContent = '';
        books.forEach(book => {
            newContent += "<button type=\"button\" class=\"btn btn-link btn-lg p-0\" onclick=\"editBookById('" + book.id + "')\">"+ book.title + "</button>";
            newContent += "<p><span>Автор: </span>";
            newContent += "<span>" + book.author + "</span>";
            newContent += "<br><span>Жанр: </span>";
            newContent += "<span>" + book.genre + "</span>";
            newContent += "<br><button type=\"button\" class=\"btn btn-secondary btn-sm\" onclick=\"deleteBookById('" + book.id + "')\">Удалить книгу</button><br></p>";
        });
        $('#booksListArea').html(newContent);
    });
}

function openingAddBookArea() {
    $("#inputId").val("");
    $("#inputTitle").val("");

    $("#inputAuthor").empty();
    fillAuthorsOptions();

    $("#inputGenre").empty();
    fillGenresOptions();

    $('#bookAddForm').attr('hidden', false);
}

function fillGenresOptions() {
    $.getJSON("/genres", function(result) {
        var options = $("#inputGenre");
        result.forEach(element => {
            options.append($("<option />").val(element.name).text(element.name));
        });
    });
}

function fillAuthorsOptions() {
    $.getJSON("/authors", function(result) {
        var options = $("#inputAuthor");
        result.forEach(element => {
            options.append($("<option />").val(element.name).text(element.name));
        });
    });
}

function onSaveBookBtnClick() {
    var newBook = Object();
    newBook.id = $('#inputId').val();
    newBook.title = $('#inputTitle').val();
    newBook.genre = $('#inputGenre').val();
    newBook.author = $('#inputAuthor').val();

    $.ajax({
        url: '/books',
        type: 'post',
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(newBook),
        // i know (Zarechnev)
        error: function(xhr, status, error) {
            reFillAllBooksArea();
        }
    });

    $('#bookAddForm').attr('hidden', true);
}

function deleteBookById(bookId) {
    $.ajax({
        url: '/books/' + bookId,
        type: 'delete',
        success: function(xhr, status, error) {
            reFillAllBooksArea();
        }
    });
}

function editBookById(bookId) {
    $.ajax({
        url: '/books/' + bookId,
        type: 'get',
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        success: function(bookToEdit) {
            $('#inputId').val(bookToEdit.id);
            $('#inputTitle').val(bookToEdit.title);

            $("#inputAuthor").empty();
            $("#inputAuthor").append($("<option />").val(bookToEdit.author).text(bookToEdit.author));
            fillAuthorsOptions();

            $("#inputGenre").empty();
            $("#inputGenre").append($("<option />").val(bookToEdit.genre).text(bookToEdit.genre));
            fillGenresOptions();

            $('#bookAddForm').attr('hidden', false);
        }
    });
}