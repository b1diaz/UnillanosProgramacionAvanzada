using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Spofy.Services;
using Spofy.ViewModels;
using Spofy.Models;

namespace Spofy.Api.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ListaController : ControllerBase
    {
        #region ---------------- Dependencias  ---------------
        private readonly ListaServices _ListaService;

        public ListaController(ListaServices ListaServices)
        {
            this._ListaService = ListaServices;
        }
        #endregion

        [HttpGet]
        public async Task<IActionResult> GetAll()
        {
            try
            {
                var lista = await _ListaService.GetAll();
                return Ok(lista);
            }
            catch (Exception ex)
            {
                return StatusCode(500, ex);
            }
        }

        [HttpPost]
        public async Task<IActionResult> Post(ListaViewModel ListaViewModel)
        {
            try
            {
                var UsuarioLista = new List<UsuarioLista>();


                var Lista = new Lista
                {
                    Nombre = ListaViewModel.Nombre,
                    UsuarioLista = UsuarioLista
                };

                await _ListaService.Create(Lista);

                var itemUsuarioLista = new UsuarioLista()
                {
                    ListaId = Lista.Id,
                    UsuarioId = ListaViewModel.UsuarioId
                };

                Lista.UsuarioLista.Add(itemUsuarioLista);

                await _ListaService.Update(Lista);

                return Ok("Lista creada correctamente");
            }
            catch (Exception ex)
            {
                return StatusCode(500, ex);
            }
        }

        [HttpPut]
        public async Task<IActionResult> Update(Lista Lista)
        {
            try
            {
                await _ListaService.Update(Lista);
                return Ok("Lista actualizada correctamente");
            }
            catch (Exception ex)
            {
                return StatusCode(500, ex);
            }
        }

        [HttpDelete]
        public async Task<IActionResult> Delete(long ListaId)
        {
            try
            {
                await _ListaService.Delete(ListaId);
                return Ok("Lista Eliminada correctamente");
            }
            catch (Exception ex)
            {
                return StatusCode(500, ex);
            }
        }
    }
}
