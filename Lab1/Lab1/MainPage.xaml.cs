using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;

namespace Lab1
{
    public partial class MainPage : ContentPage
    {
        public MainPage()
        {
            InitializeComponent();
        }


        private void Test1_OnClicked(object sender, EventArgs e)
        {
            test1();
        }

        private void Test2_OnClicked(object sender, EventArgs e)
        {
            test2();
        }

        public bool test1()
        {
            return true;
        }

        public bool test2()
        {
            return true;
        }

        private async void Test3_OnClicked(object sender, EventArgs e)
        {
            await DisplayAlert("Truth", "Yes\r\nIt's a test!", "Okay, thanks!");
        }
    }
}
