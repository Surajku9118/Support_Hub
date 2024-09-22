
    document.addEventListener('DOMContentLoaded', function () {
      const memorialEntries = document.getElementById('memorial-entries');
      const searchBox = document.getElementById('search-box');
      const pagination = document.getElementById('pagination');
      const branchFilterButtons = document.querySelectorAll('.btn-group .btn');

      let memorials = [];
      const entriesPerPage = 6;
      let currentPage = 1;
      let currentBranchFilter = "all";

      fetch('memorials.json')
        .then(response => response.json())
        .then(data => {
          memorials = data;
          displayMemorials();
          setupPagination();
        })
        .catch(error => {
          console.error('There has been a problem with your fetch operation:', error);
        });

      function displayMemorials() {
        const startIndex = (currentPage - 1) * entriesPerPage;
        const endIndex = startIndex + entriesPerPage;
        const filteredMemorials = memorials.filter(memorial => {
          const matchesSearch = memorial.name.toLowerCase().includes(searchBox.value.toLowerCase());
          const matchesBranch = currentBranchFilter === "all" || memorial.branch === currentBranchFilter;
          return matchesSearch && matchesBranch;
        });

        memorialEntries.innerHTML = '';

        filteredMemorials.slice(startIndex, endIndex).forEach(memorial => {
          const entryElement = document.createElement('div');
          entryElement.className = 'col-md-4 mb-4';
          entryElement.innerHTML = `
                        <div class="card h-100">
                            <img src="${memorial.photo || 'placeholder.jpg'}" class="card-img-top" alt="${memorial.name}">
                            <div class="card-body">
                                <h5 class="card-title">${memorial.rank} ${memorial.name}</h5>
                                <h6 class="card-subtitle mb-2 text-muted">${memorial.branch}</h6>
                                <p class="card-text"><small class="text-muted">Date of Sacrifice: ${memorial.date}</small></p>
                                <p class="card-text">${memorial.tribute}</p>
                            </div>
                        </div>
                    `;
          memorialEntries.appendChild(entryElement);
        });

        updatePagination(filteredMemorials.length);
      }

      function updatePagination(totalItems) {
        pagination.innerHTML = '';

        const pageCount = Math.ceil(totalItems / entriesPerPage);

        for (let i = 1; i <= pageCount; i++) {
          const li = document.createElement('li');
          li.classList.add('page-item');
          const link = document.createElement('a');
          link.classList.add('page-link');
          link.href = '#';
          link.textContent = i;
          link.addEventListener('click', function (event) {
            event.preventDefault();
            currentPage = i;
            displayMemorials();
          });
          li.appendChild(link);
          pagination.appendChild(li);
        }
      }

      function setupPagination() {
        const totalItems = memorials.length;
        updatePagination(totalItems);
      }

      searchBox.addEventListener('input', function () {
        displayMemorials();
      });

      branchFilterButtons.forEach(button => {
        button.addEventListener('click', () => {
          currentBranchFilter = button.dataset.branch;
          currentPage = 1;

          branchFilterButtons.forEach(btn => btn.classList.remove('active'));
          button.classList.add('active');

          displayMemorials();
        });
      });

      function handleFileUpload(file, callback) {
        const reader = new FileReader();
        reader.onload = function (e) {
          callback(e.target.result);
        };
        reader.readAsDataURL(file);
      }

      const memorialForm = document.getElementById('memorial-form');
      memorialForm.addEventListener('submit', function (e) {
        e.preventDefault();
        const heroPhotoFile = document.getElementById('hero-photo-file').files[0];

        if (heroPhotoFile) {
          handleFileUpload(heroPhotoFile, function (dataUrl) {
            const newMemorial = {
              name: document.getElementById('hero-name').value,
              rank: document.getElementById('hero-rank').value,
              branch: document.getElementById('hero-branch').value,
              date: document.getElementById('hero-date').value,
              photo: dataUrl,
              tribute: document.getElementById('hero-tribute').value
            };
            memorials.push(newMemorial);
            displayMemorials();
            memorialForm.reset();
          });
        } else {
          const newMemorial = {
            name: document.getElementById('hero-name').value,
            rank: document.getElementById('hero-rank').value,
            branch: document.getElementById('hero-branch').value,
            date: document.getElementById('hero-date').value,
            photo: '', // Default to empty if no photo is uploaded
            tribute: document.getElementById('hero-tribute').value
          };
          memorials.push(newMemorial);
          displayMemorials();
          memorialForm.reset();
        }
      });
    });

