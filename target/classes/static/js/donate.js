
    document.addEventListener("DOMContentLoaded", function () {
        // Example data for countries, states, and districts
        const data = {
            "India": {
                "Maharashtra": ["Mumbai", "Pune", "Nagpur"],
                "Karnataka": ["Bangalore", "Mysore", "Mangalore"],
                "Tamil Nadu": ["Chennai", "Coimbatore", "Madurai"]
            },
            "United States": {
                "California": ["Los Angeles", "San Francisco", "San Diego"],
                "New York": ["New York City", "Buffalo", "Rochester"],
                "Texas": ["Houston", "Dallas", "Austin"]
            },
            "Canada": {
                "Ontario": ["Toronto", "Ottawa", "Hamilton"],
                "Quebec": ["Montreal", "Quebec City", "Gatineau"],
                "British Columbia": ["Vancouver", "Victoria", "Richmond"]
            },
            "Australia": {
                "New South Wales": ["Sydney", "Newcastle", "Wollongong"],
                "Victoria": ["Melbourne", "Geelong", "Ballarat"],
                "Queensland": ["Brisbane", "Gold Coast", "Cairns"]
            },
            "United Kingdom": {
                "England": ["London", "Manchester", "Birmingham"],
                "Scotland": ["Edinburgh", "Glasgow", "Aberdeen"],
                "Wales": ["Cardiff", "Swansea", "Newport"]
            },
            "Germany": {
                "Bavaria": ["Munich", "Nuremberg", "Augsburg"],
                "Berlin": ["Berlin"],
                "North Rhine-Westphalia": ["Cologne", "Düsseldorf", "Dortmund"]
            },
            "France": {
                "Île-de-France": ["Paris", "Versailles", "Saint-Denis"],
                "Provence-Alpes-Côte d'Azur": ["Marseille", "Nice", "Toulon"],
                "Auvergne-Rhône-Alpes": ["Lyon", "Grenoble", "Saint-Étienne"]
            }
            // Add more countries and states as needed
        };

        const countrySelect = document.getElementById("country");
        const stateSelect = document.getElementById("state");
        const districtSelect = document.getElementById("district");

        // Function to populate options
        function populateOptions(selectElement, options) {
            selectElement.innerHTML = ''; // Clear previous options
            const defaultOption = document.createElement("option");
            defaultOption.value = "";
            defaultOption.textContent = "Select " + selectElement.getAttribute("data-type");
            selectElement.appendChild(defaultOption);

            if (options) {
                options.forEach(optionValue => {
                    const option = document.createElement("option");
                    option.value = optionValue;
                    option.textContent = optionValue;
                    selectElement.appendChild(option);
                });
            }
        }

        // Populate states based on selected country
        countrySelect.addEventListener("change", function () {
            const selectedCountry = this.value;
            const states = data[selectedCountry] ? Object.keys(data[selectedCountry]) : [];
            populateOptions(stateSelect, states);
            populateOptions(districtSelect, []); // Reset district options
        });

        // Populate districts based on selected state
        stateSelect.addEventListener("change", function () {
            const selectedState = this.value;
            const selectedCountry = countrySelect.value;
            const districts = data[selectedCountry] && data[selectedCountry][selectedState] ? data[selectedCountry][selectedState] : [];
            populateOptions(districtSelect, districts);
        });

        // Initialize country options
        populateOptions(countrySelect, Object.keys(data));
    });
    //
 
  document.addEventListener('DOMContentLoaded', function() {
    const paymentMethodSelect = document.getElementById('paymentMethod');
    const creditCardInfo = document.getElementById('creditCardInfo');
    const qrCodeInfo = document.getElementById('qrCodeInfo');
    const otherPaymentInfo = document.getElementById('otherPaymentInfo');

    function updatePaymentOptions() {

      const selectedMethod = paymentMethodSelect.value;

      // Hide all payment details sections
      creditCardInfo.style.display = 'none';
      qrCodeInfo.style.display = 'none';
      otherPaymentInfo.style.display = 'none';

      // Show the relevant payment details section
      switch (selectedMethod) {
        case 'credit-card':
          creditCardInfo.style.display = 'block';
          break;
        case 'qr-code':
          qrCodeInfo.style.display = 'block';
          break;
        case 'other':
          otherPaymentInfo.style.display = 'block';
          break;
      }
    }

    // Set up event listener for changes to the payment method select
    paymentMethodSelect.addEventListener('change', updatePaymentOptions);

    // Initialize display based on the current selection
    updatePaymentOptions();
  });

  document.addEventListener('DOMContentLoaded', function() {
      const agreeTermsCheckbox = document.getElementById('agreeTermsCheckbox');
      const paymentOptions = document.getElementById('paymentOptions');
      
      agreeTermsCheckbox.addEventListener('change', function() {
          if (this.checked) {
              paymentOptions.style.display = 'block';
          } else {
              paymentOptions.style.display = 'none';
          }
      });
  });


