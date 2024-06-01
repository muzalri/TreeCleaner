// document.getElementById('openPopup').addEventListener('click', function() {
//    document.getElementById('popup').style.display = 'block';
//  });
  
//  document.getElementById('closePopup').addEventListener('click', function() {
//    document.getElementById('popup').style.display = 'none';
//  });

//  document.getElementById('openPopup2').addEventListener('click', function() {
//    document.getElementById('popup2').style.display = 'block';
//  });
  
//  document.getElementById('closePopup2').addEventListener('click', function() {
//    document.getElementById('popup2').style.display = 'none';
//  });

// Fungsi untuk membuka pop-up
function openPopup(popupId) {
    document.querySelector(popupId).style.display = 'block';
}

// Fungsi untuk menutup pop-up
function closePopup(popupId) {
    document.querySelector(popupId).style.display = 'none';
}

// Event listener untuk tombol "show more"
document.querySelectorAll('.btn-grey').forEach(function(button) {
    button.addEventListener('click', function() {
        var popupId = button.getAttribute('data-target');
        openPopup(popupId);
    });
});

// Event listener untuk tombol close (×)
document.querySelectorAll('.popup .close').forEach(function(closeButton) {
    closeButton.addEventListener('click', function() {
        var popup = closeButton.closest('.popup');
        closePopup('#' + popup.id);
    });
});

// Tambahkan ini untuk menutup pop-up ketika pengguna mengklik di luar konten
window.addEventListener('click', function(event) {
    document.querySelectorAll('.popup').forEach(function(popup) {
        if (event.target === popup) {
            closePopup('#' + popup.id);
        }
    });
});